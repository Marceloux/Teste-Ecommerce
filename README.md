# Teste-Ecommerce
Para ter acesso ao código acesse a branch master.

Para ter acesso a todas as funcionalidades, e endspoints de pesquisa, é necessario que o programa esteja rodando em seu local. Clone o repositório e importe para sua IDE de preferencia, e compile o projeto. Ele será startado na porta 8080.

Também é necessário ter as seguintes ferramentas na sua maquina: Java 1.8, MySQL e PostMan.

##Consulta de Endereço a partir de um CEP
1- Ir ao postman colocar a URL http://localhost:8080/cep/consulta?cep='SEU_CEP' (colocar o CEP desejado, sem as aspas simples). Esta URL irá retornar os dados do endereço a partir do CEP enviado como parâmetro. 

Ex.: 
URL: http://localhost:8080/cep/consulta?cep=09120330

Retorno:

{
  "cep": "09120-330",
  "logradouro": "Rua Kalil Filho",
  "complemento": "",
  "bairro": "Parque Gerassi",
  "localidade": "Santo André",
  "uf": "SP",
  "ibge": "3547809",
  "gia": "6269",
  "ddd": "11",
  "siafi": "7057"
}

##Cadastro de Endereço por representado por um E-mail
2- Para o próximo passo, será necessario cadastrar um cliente, digitar http://localhost:8080/cep/cadastraCliente esta será um método POST, e preencher o objeto cliente conforme o exemplo.

ex: {
      "nome": "Marcelo Borges",
      "email": "Borges.marcelofelipe@gmail.com"
     }

##Consulta da lista de Endereços a partir de um E-mail
3-  Para cadastrar um endereço, basta digitar http://localhost:8080/cep/cadastraEndereco

ex: {
     "logradouro": "Rua, Av, Largo...",
      "numero": 00,
      "bairro": "Centro",
      "cidade": "Sao Paulo",
      "estado": "Sao paulo",
      "cep": "09120330",
      "cliente": {
        "email": "Borges.marcelofelipe@gmail.com"
      }
     }
     
   Então o endereço será cadastrado ao usuario com aquele E-mail.
    
4- E por fim, para ver a lista de endereços por cliente, digite http://localhost:8080/cep/consulta/cliente execute como POST, com o corpo da requsição
conforme o exemplo a seguir:

    {
      "email": "Borges.marcelofelipe@gmail.com"
    }
