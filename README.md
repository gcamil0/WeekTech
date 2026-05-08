# 📱 Tech Week — App Android Nativo

Aplicativo mobile desenvolvido como projeto acadêmico do **5º Semestre de ADS — UniCesumar**.
Evolução do portal web desenvolvido pelo 3º semestre, migrado para Android Nativo com persistência local via Room (offline-first).

---

## 🚀 Como rodar o projeto

### Pré-requisitos
- Android Studio Hedgehog (2023.1.1) ou superior
- JDK 8+
- SDK Android mínimo: API 24 (Android 7.0)
- Dispositivo físico ou emulador com Android 7.0+

### Passos

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/techweek-app.git

# 2. Abra o Android Studio
# File > Open > selecione a pasta techweek-app

# 3. Aguarde o Gradle sincronizar as dependências

# 4. Execute no emulador ou dispositivo
# Run > Run 'app'  (ou Shift+F10)
```

### Credenciais de admin (para teste)
```
Usuário: admin
Senha:   techweek2024
```

---

## 📚 Bibliotecas utilizadas

| Biblioteca | Versão | Finalidade |
|---|---|---|
| Room | 2.6.1 | Banco de dados local (SQLite) |
| Material Design | 1.11.0 | Componentes de UI (botões, inputs, cards) |
| RecyclerView | 1.3.2 | Listas performáticas com ViewHolder |
| CardView | 1.0.0 | Cards de conteúdo |
| AppCompat | 1.6.1 | Compatibilidade retroativa |
| ConstraintLayout | 2.1.4 | Layouts responsivos |

---

## 🏗️ Arquitetura

```
com.unicesumar.techweek
│
├── data/
│   ├── entity/          → @Entity: Participante, Palestra, Projeto, Presenca
│   ├── dao/             → @Dao: interfaces de acesso ao banco
│   └── database/        → AppDatabase (singleton Room) + SeedCallback
│
├── ui/
│   ├── evento/          → MainActivity, PalestraAdapter, ConfirmarPresencaActivity
│   ├── cadastro/        → CadastroActivity (RF02 + RF08)
│   ├── projetos/        → CadastroProjetoActivity (RF09)
│   ├── faq/             → FaqActivity (RF05)
│   └── admin/           → LoginAdminActivity + PainelAdminActivity (RF07)
│
└── util/
    └── Validators.java  → Validações com RegEx (RA, código de check-in)
```

---

## ✅ Requisitos implementados

| Requisito | Status | Observação |
|---|---|---|
| RF01 — Informações do Evento | ✅ | Programação + palestrantes na MainActivity |
| RF02 — Cadastro Participante | ✅ | CadastroActivity com validação de RA |
| RF03 — Cadastro Palestrante | ✅ | Dados populados via SeedCallback (offline-first) |
| RF04 — Localização | ✅ | Link para Google Maps no menu |
| RF05 — FAQ | ✅ | Accordion com 6 perguntas |
| RF06 — Confirmar Presença | ✅ | Check-in por código único por palestra |
| RF07 — Área Admin | ✅ | Login + painel com lista e totais |
| RF08 — Coffee Break | ✅ | Checkbox no cadastro + relatório no admin |
| RF09 — Cadastro Projeto | ✅ | Formulário com validação de RA duplicado |
| RNF01 — Responsividade | ✅ | ScrollView + ConstraintLayout |
| RNF02 — Desempenho | ✅ | Threads separadas para operações Room |
| RNF05 — Android 24 | ✅ | minSdk = 24 |
| RNF06 — Java + XML | ✅ | 100% Java + layouts XML |
| RNF07 — Armazenamento | ✅ | Room (SQLite local) |

---

## 🆕 Diferenciais Mobile (evolução do 3º semestre)

- **Offline-first**: dados carregam sem internet (Room/SQLite)
- **Favoritar palestras**: salvo localmente no banco
- **Check-in por código**: confirma presença de forma segura
- **RecyclerView + ViewHolder**: lista performática vs. tabela HTML
- **Validação por RegEx**: RA e código de check-in validados no cliente
- **Seed automático**: evento já carregado na primeira abertura do app
- **Threads separadas**: operações de banco nunca travam a UI (main thread)

---

## 👥 Equipe

| Integrante | Papel |
|---|---|
| Guilherme Camilo | Lead Mobile Developer |
| Luiz Ricardo | Database Architect |
| Matheus Gangini | System Analyst (Refinador) |
| Adler Koneski | QA & Performance Engineer |
| Kaio Vitor | UI Designer + Documentação |
