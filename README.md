# Pi Analyzer Api 📝👨‍💻

Essa api foi desenvolvida com a finalidade de realizar a análise do número pi retornando a maior sequência de números primos em suas casas decimais!

## 💡 O que é preciso para executar?

Serão necessárias as seguintes tecnologias:

- JDK 17 ⚙
- Maven 3.8.1 🍂



## 🔨 Build do projeto

Para realizar o build do projeto com o Maven, execute o seguinte comando:

```java

mvn clean install
```

## 📚 Documentação

Para acessar a documentação da api importe as collection do postman:
- piAnalizerProject.postman_collection.json

## 🤔 Manual de Utilização

Abaixo uma breve explicaçõ do funcionamento dos endpoins:
- Post uploadFile -> Faz o upload do arquivo para leitura;
- Get FileNames -> Busca o nome de todos os arquivos armazenados;
- Get BestSequence -> Reliza o processamento e retorna a sequência mais longa de números primos.