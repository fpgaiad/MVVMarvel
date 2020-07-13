# MVVMarvel

Projeto com [API da Marvel](https://developer.marvel.com/) utilizando:
* MVVM;
* Koin;
* Room;
* ViewModel;
* LiveData;
* DataBinding;
* Rx;
* Retrofit;
* Coroutines.

## Funcionalidades

Desenvolvido em Kotlin, com as seguintes funcionalidades:

* Exibe uma lista de personagens, com paginação (infinite scrolling);
* Personagens podem ser favoritados para consulta offline;
* Ao clicar no card do personagem, exibe a tela de detalhes deste personagem;
* É possível fazer busca por nome do personagem.

## Instruções para rodar o projeto

[Gere sua API key](https://developer.marvel.com/), crie o arquivo ``gradle.properties`` no diretório raiz e adicione as seguintes linhas no arquivo, conforme abaixo. Feito isso, o projeto estará pronto para ser executado no emulador ou smartphone.

---
**_gradle.properties:_**
```
orderBy="name"
timeStamp="[insira_seu_time_stamp]"
publicKey="[insira_sua_chave_pública]"
hash="[insira_seu_hash]"
```
---

By [Felipe Peixoto Gaiad](https://www.linkedin.com/in/fpgaiad/)
