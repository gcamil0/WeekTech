# Documento de Refinamento de Requisitos
## Tech Week — 5º Semestre ADS | UniCesumar

---

## Tabela Comparativa: Web (3º Sem) → Mobile Nativo (5º Sem)

| # | Requisito Original (3º Sem) | Refinamento Técnico (5º Sem) | Justificativa Sênior |
|---|---|---|---|
| RF01 | Exibir programação do evento em página web com horários, palestrantes, projetos e patrocinadores. | Programação carregada via Room (SQLite) na MainActivity com RecyclerView + ViewHolder. Dados populados no SeedCallback na primeira execução. Palestras são favoráveis com persistência local. | Mobile exige dados disponíveis offline. RecyclerView com ViewHolder recicla views e evita OutOfMemoryError em listas longas — algo impossível de garantir com tabelas HTML simples. |
| RF02 | Formulário web (HTML) para inscrição com campos Nome, RA, Curso e Série. | Activity Android com TextInputLayout (Material Design), Spinner para curso/série e validação de RA via RegEx (`^\d{8}$`). Dados persistidos no Room com verificação de RA duplicado em background thread. | Formulários web dependem de conexão. No mobile, o dado é validado localmente e salvo offline. A verificação de duplicidade garante integridade sem precisar de backend. |
| RF03 | Cadastro de palestrante via painel web. | Palestrantes cadastrados diretamente no SeedCallback (dados do evento são fixos). Exibidos na RecyclerView com bio completa. | No contexto acadêmico, os dados dos palestrantes são definidos antes do evento. O SeedCallback garante que o app já funciona na primeira abertura, sem necessidade de internet ou cadastro manual. |
| RF04 | Link para Google Maps com endereço da universidade na página web. | Intent `ACTION_VIEW` com URI do Google Maps disparado a partir do menu de navegação inferior. | No Android, deeplinks nativos abrem o Google Maps instalado diretamente, com melhor UX do que abrir um navegador. Sem dependência de iframe ou embed. |
| RF05 | Seção de FAQ com perguntas e respostas em HTML. | Activity com accordion nativo: CardView clicável que alterna `VISIBLE`/`GONE` na resposta. Sem JavaScript — animação gerenciada pela própria View. | Android não tem JavaScript nativo. O padrão correto é manipular a visibilidade de Views. O resultado é mais fluido e sem overhead de WebView. |
| RF06 | "Deve ser planejado um meio de confirmar presença" (sem definição técnica). | Sistema de check-in por código único por palestra (formato `TW2024-NN`). Participante informa RA + código; sistema valida no Room e registra na tabela `presencas` com chave estrangeira para participante e palestra. Impede duplo check-in via query `jaConfirmou()`. | A web não tem como garantir que só o participante presente confirma — qualquer um com o link poderia confirmar. Com código gerado na hora e validação local, o check-in é auditável e à prova de fraude. |
| RF07 | Login de admin na web para acompanhar inscrições. | LoginAdminActivity com validação de credenciais fixas (escopo acadêmico). PainelAdminActivity com RecyclerView de inscritos, contadores em tempo real e filtro de Coffee Break. Toda leitura feita em background thread via `Executors`. | Credenciais fixas são aceitáveis para escopo acadêmico. O padrão de produção seria hash SHA-256 no Room. O painel mobile carrega mais rápido que uma tabela HTML paginada por servidor. |
| RF08 | Campo de Coffee Break no cadastro web + relatório. | CheckBox no formulário de cadastro (`CadastroActivity`) com campo `coffeeBreak` na `@Entity Participante`. Relatório acessível no painel admin com filtro via query `listarCoffeeBreak()`. | A query Room retorna apenas quem optou, sem overhead de filtro no front. No web seria necessária uma requisição extra ao servidor. |
| RF09 | Formulário web para cadastro de projeto com Nome, RA, Nome do Projeto e Descrição. | `CadastroProjetoActivity` com validação completa. Restrição de 1 projeto por RA via `buscarPorRa()` antes do insert. Dados persistidos na `@Entity Projeto`. | Garante integridade de dados localmente sem servidor. A restrição de unicidade por RA é verificada na camada de aplicação antes de gravar no banco. |

---

## Diferenciais Mobile Adicionados (sem equivalente no 3º Semestre)

| Diferencial | Tecnologia | Benefício |
|---|---|---|
| Offline-first | Room + SeedCallback | App funciona sem internet desde o primeiro acesso |
| Favoritar palestras | Room (@Query UPDATE) | Usuário salva preferências localmente |
| Validação por RegEx | `java.util.regex.Pattern` | RA e código de check-in validados antes de qualquer operação no banco |
| Background threads | `Executors.newSingleThreadExecutor()` | Operações Room nunca bloqueiam a UI thread (evita ANR) |
| RecyclerView + ViewHolder | `RecyclerView.ViewHolder` | Performance superior a ListView ou tabela HTML — reutiliza views |
| Seed automático | `RoomDatabase.Callback` | Dados do evento disponíveis offline na primeira execução |

---

## Decisões de Arquitetura

**Por que Room e não Firebase?**
O requisito RNF07 pede armazenamento de dados e o RNF02 pede carregamento rápido via rede móvel. Room entrega os dois: dados locais = zero latência de rede e funciona offline. Firebase exigiria conexão constante e adicionaria complexidade de autenticação fora do escopo.

**Por que Executors e não AsyncTask?**
`AsyncTask` foi depreciado no Android API 30. A alternativa correta em Java puro (sem Coroutines Kotlin) é `Executors.newSingleThreadExecutor()`, que isola operações de I/O da thread principal e evita `NetworkOnMainThreadException`.

**Por que credenciais fixas no Admin?**
Escopo acadêmico. Em produção, a senha seria armazenada como hash `SHA-256` em uma tabela `@Entity Admin` no Room, comparada localmente sem tráfego de rede.
