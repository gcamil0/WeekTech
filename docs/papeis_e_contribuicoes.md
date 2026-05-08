# Relatório de Papéis, Commits e Contribuições
## Tech Week — 5º Semestre ADS | UniCesumar

---

## Estrutura da Equipe

| Integrante | Papel | Responsabilidade Principal |
|---|---|---|
| **Guilherme Camilo** | Lead Mobile Developer | Activities principais, navegação, integração geral |
| **Luiz Ricardo** | Database Architect | Entities, DAOs, AppDatabase, SeedCallback |
| **Matheus Gangini** | System Analyst (Refinador) | Documento de refinamento, issues, comissão de requisitos |
| **Adler Koneski** | QA & Performance Engineer | Validações (Validators.java), threads, testes manuais |
| **Kaio Vitor** | UI Designer + Documentação | Layouts XML, identidade visual, README, DER |

---

## Histórico de Commits por Integrante

### 🔵 Luiz Ricardo — Database Architect
> Branch sugerida: `feature/database`

```
git commit -m "chore: setup inicial do projeto Android (SDK 24, Java, Room)"

git commit -m "feat(db): adiciona @Entity Participante com campos nome, ra, curso, serie, coffeeBreak"

git commit -m "feat(db): adiciona @Entity Palestra com campos titulo, palestrante, horario, local, codigoCheckin, favoritada"

git commit -m "feat(db): adiciona @Entity Projeto"

git commit -m "feat(db): adiciona @Entity Presenca com @ForeignKey para Participante e Palestra"

git commit -m "feat(db): implementa ParticipanteDao com queries de insert, listagem e filtros"

git commit -m "feat(db): implementa PalestraDao com favoritar e busca por código"

git commit -m "feat(db): implementa ProjetoDao e PresencaDao"

git commit -m "feat(db): implementa AppDatabase singleton com Room.databaseBuilder"

git commit -m "feat(db): adiciona SeedCallback para popular palestras na primeira execução (offline-first)"
```

---

### 🟠 Guilherme Camilo — Lead Mobile Developer
> Branch sugerida: `feature/activities`

```
git commit -m "feat(ui): implementa MainActivity com RecyclerView de palestras e BottomNavigationView"

git commit -m "feat(ui): implementa PalestraAdapter com ViewHolder pattern e listener de favorito"

git commit -m "feat(ui): carrega palestras do Room em background thread (Executors)"

git commit -m "feat(ui): adiciona intent para Google Maps (RF04 - localização)"

git commit -m "feat(ui): implementa menu com acesso ao painel admin"

git commit -m "feat(cadastro): implementa CadastroActivity com formulário (RF02)"

git commit -m "feat(cadastro): adiciona CheckBox de Coffee Break integrado ao cadastro (RF08)"

git commit -m "feat(cadastro): insere participante no Room em background thread com verificação de RA duplicado"

git commit -m "feat(projetos): implementa CadastroProjetoActivity (RF09)"

git commit -m "feat(checkin): implementa ConfirmarPresencaActivity com validação de código e RA (RF06)"
```

---

### 🟢 Adler Koneski — QA & Performance Engineer
> Branch sugerida: `feature/validation`

```
git commit -m "feat(util): implementa Validators.java com RegEx para RA (^\d{8}$)"

git commit -m "feat(util): adiciona validação de código de check-in (^TW\d{4}-\d{2}$)"

git commit -m "feat(util): adiciona helpers campoVazio() e nomeValido()"

git commit -m "fix(cadastro): aplica Validators no CadastroActivity antes do insert"

git commit -m "fix(checkin): aplica Validators no ConfirmarPresencaActivity antes da query Room"

git commit -m "fix(projeto): aplica Validators no CadastroProjetoActivity"

git commit -m "perf: garante que todas as operações Room rodam fora da main thread"

git commit -m "test: testes manuais de fluxo completo (cadastro → check-in → painel admin)"
```

---

### 🟣 Kaio Vitor — UI Designer + Documentação
> Branch sugerida: `feature/ui-layouts`

```
git commit -m "feat(layout): adiciona colors.xml com identidade visual UniCesumar (azul #003B8E, laranja #FF6B00)"

git commit -m "feat(layout): configura themes.xml com MaterialComponents"

git commit -m "feat(layout): implementa activity_main.xml com AppBarLayout, RecyclerView e BottomNavigationView"

git commit -m "feat(layout): implementa item_palestra.xml com CardView e botão de favorito"

git commit -m "feat(layout): implementa activity_cadastro.xml com TextInputLayout Material Design"

git commit -m "feat(layout): implementa activity_cadastro_projeto.xml"

git commit -m "feat(layout): implementa activity_faq.xml com cards accordion"

git commit -m "feat(layout): implementa activity_login_admin.xml e activity_painel_admin.xml"

git commit -m "feat(layout): implementa activity_confirmar_presenca.xml e item_participante_admin.xml"

git commit -m "docs: adiciona README.md com instruções de execução e bibliotecas"
```

---

### 🔴 Matheus Gangini — System Analyst (Refinador)
> Branch sugerida: `docs/refinamento`

```
git commit -m "docs: levantamento de requisitos baseado na documentação do 3º semestre"

git commit -m "docs: cria issues no GitHub com título, descrição e critérios de aceitação"

git commit -m "docs: elabora tabela comparativa Web vs Mobile (refinamento_requisitos.md)"

git commit -m "docs: documenta decisões de arquitetura (Room vs Firebase, Executors vs AsyncTask)"

git commit -m "docs: adiciona diferenciais mobile (offline-first, favoritar, check-in por código)"

git commit -m "docs: finaliza relatório de papéis e contribuições"

git commit -m "docs: revisa e valida todos os requisitos implementados vs. documentados"
```

---

## Maiores Desafios Técnicos

**1. Herança do projeto web sem código-fonte mobile**
O maior desafio foi partir do zero no Android sem uma base de código para migrar. A solução foi mapear cada requisito web para seu equivalente nativo antes de escrever qualquer linha de código.

**2. Room em background thread**
Tentativas iniciais de acessar o DAO na main thread geravam `IllegalStateException`. A solução foi mover todas as operações Room para `Executors.newSingleThreadExecutor()` e usar `runOnUiThread()` para atualizar a UI após a resposta.

**3. SeedCallback e instância circular do banco**
O `SeedCallback` precisava acessar o próprio `AppDatabase` para inserir dados — mas o banco ainda estava sendo construído. A solução foi passar o `Context` pelo construtor do SeedCallback e chamar `getInstance()` dentro do método `onCreate()`, que executa depois que o banco é criado.

**4. Impedir check-in duplicado**
A query `jaConfirmou(participanteId, palestraId)` resolve isso consultando a tabela `presencas` antes de inserir. Simples e eficaz sem precisar de constraints de banco adicionais.
