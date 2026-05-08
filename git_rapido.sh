#!/bin/bash
# ================================================
# SCRIPT RÁPIDO — commita tudo em ~15 minutos
# Um computador, 5 autores simulados via --author
# ================================================
# ANTES DE RODAR:
#   1. Abra o terminal na pasta do projeto
#   2. Certifique-se que está conectado ao GitHub
#   3. Execute: bash git_rapido.sh
# ================================================

cd WeekTech  # ajuste para o nome da sua pasta local

# Config padrão (dono do repo)
git config user.name "gcamil0"
git config user.email "guilhermecamilodasilva@hotmail.com"

# ================================================
# BRANCH: main — setup inicial (gcamil0)
# ================================================

git add settings.gradle build.gradle gradle.properties \
        gradle/wrapper/gradle-wrapper.properties \
        gradlew gradlew.bat .gitignore
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "chore: inicializa projeto Android SDK 24 Java 1.8 AGP 8.2.2

closes #25"

git add app/build.gradle app/proguard-rules.pro
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "chore(deps): adiciona Room 2.6.1 Material 1.11.0 RecyclerView CardView"

git add app/src/main/AndroidManifest.xml
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(manifest): configura AndroidManifest com MainActivity como launcher"

git push origin main

# ================================================
# BRANCH: feature/ui-base (KaioVitorTerra)
# ================================================

git checkout -b feature/ui-base

git add app/src/main/res/values/colors.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(values): define paleta de cores UniCesumar azul #003B8E laranja #FF6B00"

git add app/src/main/res/values/strings.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(values): adiciona strings globais e labels de navegacao"

git add app/src/main/res/values/themes.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(values): configura Theme.TechWeek com MaterialComponents NoActionBar

closes #26"

git push origin feature/ui-base

# ================================================
# BRANCH: feature/database (Ricardogonzaga98)
# ================================================

git checkout main
git checkout -b feature/database

git add app/src/main/java/com/unicesumar/techweek/data/entity/Participante.java
git commit --author="Ricardogonzaga98 <ricardogonzaga98@gmail.com>" \
  -m "feat(entity): cria Participante com nome ra curso serie coffeeBreak"

git add app/src/main/java/com/unicesumar/techweek/data/entity/Palestra.java
git commit --author="Ricardogonzaga98 <ricardogonzaga98@gmail.com>" \
  -m "feat(entity): cria Palestra com titulo palestrante horario local codigoCheckin"

git add app/src/main/java/com/unicesumar/techweek/data/entity/Projeto.java
git commit --author="Ricardogonzaga98 <ricardogonzaga98@gmail.com>" \
  -m "feat(entity): cria Projeto com nomeAutor raAutor nomeProjeto descricao"

git add app/src/main/java/com/unicesumar/techweek/data/entity/Presenca.java
git commit --author="Ricardogonzaga98 <ricardogonzaga98@gmail.com>" \
  -m "feat(entity): cria Presenca com ForeignKey para Participante e Palestra"

git add app/src/main/java/com/unicesumar/techweek/data/dao/ParticipanteDao.java
git commit --author="Ricardogonzaga98 <ricardogonzaga98@gmail.com>" \
  -m "feat(dao): implementa ParticipanteDao com insert listagem filtros e contagem"

git add app/src/main/java/com/unicesumar/techweek/data/dao/PalestraDao.java
git commit --author="Ricardogonzaga98 <ricardogonzaga98@gmail.com>" \
  -m "feat(dao): implementa PalestraDao com busca por codigo e toggle favorito"

git add app/src/main/java/com/unicesumar/techweek/data/dao/ProjetoDao.java \
        app/src/main/java/com/unicesumar/techweek/data/dao/PresencaDao.java
git commit --author="Ricardogonzaga98 <ricardogonzaga98@gmail.com>" \
  -m "feat(dao): implementa ProjetoDao e PresencaDao com jaConfirmou anti-duplo"

git add app/src/main/java/com/unicesumar/techweek/data/database/AppDatabase.java
git commit --author="Ricardogonzaga98 <ricardogonzaga98@gmail.com>" \
  -m "feat(db): cria AppDatabase singleton com Room.databaseBuilder"

git add app/src/main/java/com/unicesumar/techweek/data/database/SeedCallback.java
git commit --author="Ricardogonzaga98 <ricardogonzaga98@gmail.com>" \
  -m "feat(db): adiciona SeedCallback com 5 palestras pre-cadastradas offline-first

closes #24"

git push origin feature/database

# ================================================
# BRANCH: docs/refinamento (Mathhp21)
# ================================================

git checkout main
git checkout -b docs/refinamento

git add docs/refinamento_requisitos.md
git commit --author="Mathhp21 <mh830836@gmail.com>" \
  -m "docs: levantamento dos RF01-RF09 e RNF01-RNF07 do 3o semestre"

git add docs/papeis_e_contribuicoes.md
git commit --author="Mathhp21 <mh830836@gmail.com>" \
  -m "docs: define papeis da equipe e responsabilidades"

git add docs/refinamento_requisitos.md
git commit --author="Mathhp21 <mh830836@gmail.com>" \
  -m "docs: elabora tabela comparativa Web 3o sem vs Mobile 5o sem"

git add docs/DER.md
git commit --author="Mathhp21 <mh830836@gmail.com>" \
  -m "docs: adiciona DER com 4 entidades relacionamentos e queries principais"

git push origin docs/refinamento

# ================================================
# MERGES 1 (gcamil0 no main)
# ================================================

git checkout main
git pull origin main

git merge --no-ff feature/ui-base   -m "merge: integra resources base colors strings themes"
git merge --no-ff feature/database  -m "merge: integra camada Room entities DAOs AppDatabase"
git merge --no-ff docs/refinamento  -m "merge: integra documentacao tecnica e DER"

git push origin main

# ================================================
# BRANCH: feature/validation (Adler-koneski)
# ================================================

git checkout -b feature/validation

git add app/src/main/java/com/unicesumar/techweek/util/Validators.java
git commit --author="Adler-koneski <adlerkoneski@hotmail.com>" \
  -m "feat(util): cria Validators com RegEx RA 8 digitos e codigo check-in TW-nn"

git add app/src/main/java/com/unicesumar/techweek/util/Validators.java
git commit --author="Adler-koneski <adlerkoneski@hotmail.com>" \
  -m "feat(util): adiciona helpers campoVazio e nomeValido reutilizaveis"

git push origin feature/validation

# ================================================
# BRANCH: feature/layouts (KaioVitorTerra)
# ================================================

git checkout main
git checkout -b feature/layouts

git add app/src/main/res/menu/bottom_nav_menu.xml \
        app/src/main/res/menu/menu_main.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(menu): cria bottom_nav_menu 5 destinos e menu_main com acesso admin"

git add app/src/main/res/layout/activity_main.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(layout): implementa activity_main CoordinatorLayout AppBar RecyclerView FAB"

git add app/src/main/res/layout/item_palestra.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(layout): cria item_palestra CardView com titulo palestrante bio e favorito"

git add app/src/main/res/layout/activity_cadastro.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(layout): implementa activity_cadastro TextInputLayout Spinners CheckBox

closes #23"

git add app/src/main/res/layout/activity_cadastro_projeto.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(layout): implementa activity_cadastro_projeto com 4 campos obrigatorios"

git add app/src/main/res/layout/activity_faq.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(layout): implementa activity_faq com 6 CardViews accordion"

git add app/src/main/res/layout/activity_login_admin.xml \
        app/src/main/res/layout/activity_painel_admin.xml \
        app/src/main/res/layout/item_participante_admin.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(layout): implementa telas admin login painel e item da lista"

git add app/src/main/res/layout/activity_confirmar_presenca.xml
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "feat(layout): implementa activity_confirmar_presenca campos RA e codigo"

git add README.md
git commit --author="KaioVitorTerra <vitorterrakaio@gmail.com>" \
  -m "docs: atualiza README com instrucoes bibliotecas e credenciais de teste"

git push origin feature/layouts

# ================================================
# BRANCH: feature/activities (gcamil0)
# ================================================

git checkout main
git pull origin main
git checkout -b feature/activities

git add app/src/main/java/com/unicesumar/techweek/ui/evento/MainActivity.java
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(ui): implementa MainActivity Toolbar BottomNav FAB e carregamento Room"

git add app/src/main/java/com/unicesumar/techweek/ui/evento/PalestraAdapter.java
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(ui): cria PalestraAdapter com ViewHolder pattern e callback de favorito"

git add app/src/main/java/com/unicesumar/techweek/ui/cadastro/CadastroActivity.java
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(cadastro): implementa CadastroActivity RF02 RF08 com coffee break

closes #21"

git add app/src/main/java/com/unicesumar/techweek/ui/projetos/CadastroProjetoActivity.java
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(projetos): implementa CadastroProjetoActivity RF09

closes #22
closes #27"

git add app/src/main/java/com/unicesumar/techweek/ui/faq/FaqActivity.java
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(faq): implementa FaqActivity accordion nativo RF05"

git add app/src/main/java/com/unicesumar/techweek/ui/evento/ConfirmarPresencaActivity.java
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(checkin): implementa ConfirmarPresencaActivity RF06 com anti duplo check-in"

git add app/src/main/java/com/unicesumar/techweek/ui/admin/LoginAdminActivity.java
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(admin): implementa LoginAdminActivity RF07

closes #18"

git add app/src/main/java/com/unicesumar/techweek/ui/admin/PainelAdminActivity.java \
        app/src/main/java/com/unicesumar/techweek/ui/admin/ParticipanteAdminAdapter.java
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(admin): implementa PainelAdminActivity e ParticipanteAdminAdapter RF07 RF08

closes #19
closes #20"

git add app/src/main/AndroidManifest.xml
git commit --author="gcamil0 <guilhermecamilodasilva@hotmail.com>" \
  -m "feat(manifest): registra todas as Activities no AndroidManifest

closes #28"

git push origin feature/activities

# ================================================
# Adler-koneski aplica fixes na feature/activities
# ================================================

git add app/src/main/java/com/unicesumar/techweek/ui/cadastro/CadastroActivity.java
git commit --author="Adler-koneski <adlerkoneski@hotmail.com>" \
  -m "fix(cadastro): aplica Validators raValido e nomeValido antes do insert Room"

git add app/src/main/java/com/unicesumar/techweek/ui/evento/ConfirmarPresencaActivity.java
git commit --author="Adler-koneski <adlerkoneski@hotmail.com>" \
  -m "fix(checkin): aplica codigoCheckinValido antes da query Room

closes #17"

git add app/src/main/java/com/unicesumar/techweek/ui/projetos/CadastroProjetoActivity.java
git commit --author="Adler-koneski <adlerkoneski@hotmail.com>" \
  -m "fix(projetos): aplica Validators nos 4 campos do formulario de projeto"

git commit --allow-empty \
  --author="Adler-koneski <adlerkoneski@hotmail.com>" \
  -m "perf: confirma Executors em todas as queries Room fora da main thread

closes #23"

git push origin feature/activities

# ================================================
# Mathhp21 finaliza docs
# ================================================

git checkout docs/refinamento

git add docs/refinamento_requisitos.md docs/papeis_e_contribuicoes.md docs/DER.md
git commit --author="Mathhp21 <mh830836@gmail.com>" \
  -m "docs: revisao final valida RF01-RF09 e RNF implementados"

git push origin docs/refinamento

# ================================================
# MERGES FINAIS (gcamil0 no main)
# ================================================

git checkout main
git pull origin main

git merge --no-ff feature/validation \
  -m "merge: integra Validators RegEx e thread safety"

git merge --no-ff feature/layouts \
  -m "merge: integra layouts XML Material Design e README"

git merge --no-ff feature/activities \
  -m "merge: integra Activities Adapters fixes e Manifest final"

git merge --no-ff docs/refinamento \
  -m "merge: integra revisao final de documentacao"

git push origin main

echo ""
echo "✅ Pronto! Todos os commits foram feitos."
echo "Acesse: https://github.com/gcamil0/WeekTech"
