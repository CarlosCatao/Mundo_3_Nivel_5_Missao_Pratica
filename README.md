<img src="./Estacio horizontal.png" align="left" height="64px" /><br><br>
# Curso: Desenvolvimento Full Stack
## Mundo 3 - Nível 5 - Missão Prática

## RPG0018  - Por que não paralelizar

**Autor:** Carlos Altomare Catao

**Curso:** Desenvolvedor Full Stack (Estácio - Mundo 3)

**Matrícula:** 202403460912

**Polo:** Santa Luzia - Vitória/ES

---

## 📌 Visão Geral

Este projeto implementa uma aplicação Java cliente-servidor para autenticação de usuários, listagem de produtos e registro de movimentações comerciais (entrada e saída de estoque), utilizando comunicação via sockets e persistência com JPA. O sistema é modular, seguro e multitarefa, com suporte a múltiplos clientes simultâneos via threads.

## 🧱 Estrutura do Projeto

- **Servidor (`CadastroServer`)**
  - Autenticação e controle de sessões
  - Gerenciamento de produtos e operações no banco de dados
  - Threads para múltiplos clientes

- **Cliente (`CadastroClient` e `CadastroClientV2`)**
  - Interface em console e modo assíncrono
  - Comunicação com servidor via `ObjectInputStream` / `ObjectOutputStream`

## ⚙️ Funcionalidades

- Login com validação no banco
- Listagem de produtos
- Registro de entrada/saída de estoque
- Controle de sessões
- Interface gráfica para exibição de mensagens
- Comunicação segura por objetos serializados

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (JDK 17+)
- **IDE:** NetBeans
- **Banco de Dados:** SQL Server (via JPA)
- **Comunicação:** TCP/IP com Sockets

## 🔗 Repositórios dos Códigos

- [Servidor – Procedimento 1](https://github.com/CarlosCatao/Mundo_3_Nivel_5_Missao_Pratica/tree/main/Procedimento-1/CadastroServer)  
- [Cliente – Procedimento 1](https://github.com/CarlosCatao/Mundo_3_Nivel_5_Missao_Pratica/tree/main/Procedimento-1/CadastroClient)  
- [Servidor – Procedimento 2](https://github.com/CarlosCatao/Mundo_3_Nivel_5_Missao_Pratica/tree/main/Procedimento-2/CadastroServer)  
- [Cliente – Procedimento 2](https://github.com/CarlosCatao/Mundo_3_Nivel_5_Missao_Pratica/tree/main/Procedimento-2/CadastroClientV2)

## 🧪 Testes

- Autenticação com dados válidos e inválidos
- Registro e consulta de movimentações
- Testes de múltiplos clientes simultâneos
- Validação de consistência no banco de dados
- Testes de robustez com entradas inválidas

## 🏁 Conclusão

O projeto alcançou seus objetivos, integrando comunicação em rede, JPA e banco de dados relacional em uma aplicação funcional. Os desafios enfrentados fortaleceram habilidades em programação distribuída, modelagem de dados, tratamento de exceções e concorrência com threads.

## 🔗 Repositório Git

> https://github.com/CarlosCatao/Mundo_3_Nivel_5_Missao_Pratica


---