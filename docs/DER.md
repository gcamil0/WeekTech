# Diagrama de Entidade-Relacionamento (DER)
## Tech Week — Banco de Dados Room (SQLite)

---

## Entidades (@Entity)

### PARTICIPANTES
```
┌─────────────────────────────────────┐
│            participantes            │
├─────────────────────────────────────┤
│ PK  id                INTEGER (AI)  │
│     nome              TEXT          │
│     ra                TEXT          │  ← único por negócio
│     curso             TEXT          │
│     serie             TEXT          │
│     coffee_break      INTEGER (0/1) │
│     presenca_confirmada INTEGER(0/1)│
└─────────────────────────────────────┘
```

### PALESTRAS
```
┌─────────────────────────────────────┐
│              palestras              │
├─────────────────────────────────────┤
│ PK  id                INTEGER (AI)  │
│     titulo            TEXT          │
│     palestrante       TEXT          │
│     bio_palestrante   TEXT          │
│     horario           TEXT          │
│     local             TEXT          │
│     codigo_checkin    TEXT          │  ← único por palestra
│     favoritada        INTEGER (0/1) │
└─────────────────────────────────────┘
```

### PROJETOS
```
┌─────────────────────────────────────┐
│               projetos              │
├─────────────────────────────────────┤
│ PK  id                INTEGER (AI)  │
│     nome_autor        TEXT          │
│     ra_autor          TEXT          │  ← único por negócio
│     nome_projeto      TEXT          │
│     descricao         TEXT          │
└─────────────────────────────────────┘
```

### PRESENÇAS
```
┌─────────────────────────────────────┐
│              presencas              │
├─────────────────────────────────────┤
│ PK  id                INTEGER (AI)  │
│ FK  participante_id   INTEGER       │──→ participantes.id
│ FK  palestra_id       INTEGER       │──→ palestras.id
└─────────────────────────────────────┘
```

---

## Relacionamentos

```
participantes ──< presencas >── palestras
    (1)              (N:N)          (1)

Um participante pode confirmar presença em N palestras.
Uma palestra pode ter N participantes com presença confirmada.
A tabela "presencas" é a tabela associativa (many-to-many).

participantes (1) ──── projetos (0..1)
Um participante pode ter no máximo 1 projeto cadastrado.
Verificado via query buscarPorRa() antes do insert.
```

---

## Diagrama Visual (ASCII)

```
┌──────────────┐         ┌──────────────┐         ┌──────────────┐
│ participantes│         │   presencas  │         │   palestras  │
├──────────────┤         ├──────────────┤         ├──────────────┤
│ PK id        │◄────────│ FK part_id   │         │ PK id        │
│ nome         │         │ FK paless_id │────────►│ titulo       │
│ ra           │         │ PK id        │         │ palestrante  │
│ curso        │         └──────────────┘         │ bio          │
│ serie        │                                  │ horario      │
│ coffee_break │                                  │ local        │
│ presenca_ok  │                                  │ cod_checkin  │
└──────┬───────┘                                  │ favoritada   │
       │ 0..1                                     └──────────────┘
       │
       ▼
┌──────────────┐
│   projetos   │
├──────────────┤
│ PK id        │
│ nome_autor   │
│ ra_autor     │
│ nome_projeto │
│ descricao    │
└──────────────┘
```

---

## Índices criados automaticamente pelo Room

| Tabela | Índice | Motivo |
|---|---|---|
| presencas | idx_participante_id | ForeignKey — busca por participante |
| presencas | idx_palestra_id | ForeignKey — busca por palestra |

---

## Queries principais por caso de uso

| Caso de Uso | Query |
|---|---|
| Listar todos os inscritos | `SELECT * FROM participantes ORDER BY nome ASC` |
| Filtrar Coffee Break | `SELECT * FROM participantes WHERE coffee_break = 1` |
| Check-in: verificar RA | `SELECT * FROM participantes WHERE ra = :ra LIMIT 1` |
| Check-in: validar código | `SELECT * FROM palestras WHERE codigo_checkin = :codigo LIMIT 1` |
| Evitar check-in duplicado | `SELECT COUNT(*) FROM presencas WHERE participante_id = :p AND palestra_id = :pal` |
| Favoritar palestra | `UPDATE palestras SET favoritada = :f WHERE id = :id` |
| Projetos por RA | `SELECT * FROM projetos WHERE ra_autor = :ra LIMIT 1` |
