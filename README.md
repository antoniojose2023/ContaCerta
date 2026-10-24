# 💰 Conta Certa

> Controle sua vida financeira de maneira fácil e segura.

**Conta Certa** é um aplicativo Android de controle financeiro pessoal. Com ele você registra receitas e despesas, acompanha o saldo do mês e mantém tudo organizado em poucos toques, sem complicação.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Material Design](https://img.shields.io/badge/Material%20Design%203-757575?style=for-the-badge&logo=materialdesign&logoColor=white)
![Room](https://img.shields.io/badge/Room-Database-4285F4?style=for-the-badge&logo=android&logoColor=white)

---

## 📑 Sumário

- [Sobre o projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Telas](#-telas)
- [Tecnologias](#-tecnologias)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Como executar](#-como-executar)
- [Como usar](#-como-usar)
- [Roadmap](#-roadmap)
- [Contribuindo](#-contribuindo)
- [Autor](#-autor)
- [Licença](#-licença)

---

## 📱 Sobre o projeto

O Conta Certa nasceu para simplificar o dia a dia financeiro. Em vez de planilhas e anotações soltas, você tem um painel único com o saldo do mês, o total de receitas e despesas e o histórico das últimas movimentações.

Os dados ficam armazenados localmente no dispositivo, usando banco de dados **Room**, então o app funciona sem internet.

## ✨ Funcionalidades

- **Dashboard** com visão geral do mês atual:
  - Saldo atual
  - Total de receitas
  - Total de despesas
  - Lista das últimas transações com atalho "Ver todas"
- **Cadastro de transações** (receitas e despesas) com nome, categoria, valor e data.
- **Listagem de transações** com:
  - Busca por nome
  - Filtros: *Todas*, *Receitas* e *Despesas*
  - Ordenação crescente/decrescente
- **Editar transação**: toque sobre a transação para abrir a tela de atualização.
- **Excluir transação**: toque e segure sobre a transação.
- **Tela de informações** com explicação do app e dicas de uso.
- Interface seguindo o **Material Design 3**, com navegação inferior (dashboard, transações e info).
- Cores semânticas: verde para receitas e laranja/vermelho para despesas.

## 🖼️ Telas

| Splash | Dashboard | Transações | Informações |
| :---: | :---: | :---: | :---: |
| <img width="1280" height="2856" alt="Image" src="https://github.com/user-attachments/assets/71071a64-a15d-46cf-ac1f-d9a04d1499fc" /> | <img width="1280" height="2856" alt="Image" src="https://github.com/user-attachments/assets/179ec30c-5501-4aaa-8588-034241b6c52a" /> | <img width="1280" height="2856" alt="Image" src="https://github.com/user-attachments/assets/82f0fa41-9e85-4763-b2dc-cb9526a7dc96" /> | <img width="1280" height="2856" alt="Image" src="https://github.com/user-attachments/assets/4af75275-7e8b-48bf-ba9b-06a5a9101b53" /> |



## 🛠️ Tecnologias

| Categoria | Tecnologia |
| --- | --- |
| Linguagem | [Kotlin](https://kotlinlang.org/) |
| Plataforma | Android (Android Studio) |
| Build | Gradle (Kotlin DSL) com Version Catalog (`libs.versions.toml`) |
| Persistência | [Room](https://developer.android.com/training/data-storage/room) |
| Processamento de anotações | [KSP](https://github.com/google/ksp) |
| Design | [Material Design 3](https://m3.material.io/) |

## 📂 Estrutura do projeto

```text
ContaCerta/
├── app/                    # Módulo principal do aplicativo Android
├── gradle/                 # Wrapper e catálogo de versões (libs.versions.toml)
├── build.gradle.kts        # Configuração de build do projeto (plugins: Android, KSP, Room)
├── settings.gradle.kts     # Configuração dos módulos
├── gradle.properties       # Propriedades do Gradle
├── gradlew / gradlew.bat   # Gradle Wrapper (Linux/macOS e Windows)
└── README.md
```

## 🚀 Como executar

### Pré-requisitos

- [Android Studio](https://developer.android.com/studio) (versão recente)
- JDK 17 ou superior (já incluso no Android Studio)
- Um emulador Android ou dispositivo físico com depuração USB ativada

### Passo a passo

1. **Clone o repositório**

   ```bash
   git clone https://github.com/antoniojose2023/ContaCerta.git
   cd ContaCerta
   ```

2. **Abra no Android Studio**

   `File > Open` e selecione a pasta `ContaCerta`. Aguarde a sincronização do Gradle.

3. **Execute o app**

   Selecione um emulador ou dispositivo e clique em ▶️ **Run**, ou use o terminal:

   ```bash
   ./gradlew installDebug
   ```

   No Windows:

   ```bash
   gradlew.bat installDebug
   ```

### Gerar APK de debug

```bash
./gradlew assembleDebug
```

O arquivo será gerado em `app/build/outputs/apk/debug/`.

## 📖 Como usar

1. Ao abrir o app, a tela de abertura é exibida e em seguida você cai no **Dashboard**.
2. Na aba **transações**, toque no botão **➕** para cadastrar uma nova receita ou despesa.
3. Use a busca e os filtros (**Todas**, **Receitas**, **Despesas**) para encontrar movimentações.
4. Altere a ordem da lista pelo botão **Ordem Crescente/Decrescente**.
5. **Toque** em uma transação para editá-la.
6. **Toque e segure** em uma transação para excluí-la.
7. Use o ícone de olho no card de saldo para ocultar ou mostrar o valor.

## 🗺️ Roadmap
- [ ] Gráficos de gastos por categoria
- [ ] Exportação de relatórios (PDF/CSV)


## 🤝 Contribuindo

Contribuições são bem-vindas!

1. Faça um fork do projeto
2. Crie uma branch para sua feature: `git checkout -b feature/minha-feature`
3. Faça commit das alterações: `git commit -m "feat: minha nova feature"`
4. Envie para a branch: `git push origin feature/minha-feature`
5. Abra um Pull Request

## 👤 Autor

**Antônio José**

- GitHub: [@antoniojose2023](https://github.com/antoniojose2023)

---

<p align="center">Feito com 💜 e Kotlin</p>
