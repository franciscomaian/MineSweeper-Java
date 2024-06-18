# Minesweeper
Jogo campo minado feito em Java utilizando GUI para a matéria SCC0204 - Programação Orientada a Objetos

Feito por:  <br/>
Francisco Luiz Maian do Nascimento - nUSP: 14570890 <br/>
Gustavo Cardozo de Moraes Moreira - nUSP: 5244057 <br/>
Matheus Rodrigues do Santos - nUSP: 11212866 <br/>

### 1.Requisitos:
* Java e JDK

### 2.Descrição:
Jogo campo minado feito em Java utilizando GUI com três dificuldades: fácil, médio e difícil, placar de melhores tempos e uma funcionalidade única de bomba especial que ativa e desativa conforme o tempo e deve ser defusada enquanto estiver desativada.

### 3.Comentários do Código:
O células do campo foram feitas a partir de uma interface chamada Cell, e como o programa principal utiliza a interface para criar o grid, algumas funções das classes que implementam a Cell não tem funcionalidade mas são necessárias para rodar o código no geral.

### 4.Plano de testes:
* **Verificação de dificuldades:** Verificar se as dificuldades realmente mudam o jogo;
* **Abrir uma célula:** Verificar se a funcionalidade de abrir uma célula está funcionando;
* **Marcar uma célula:** Verificar se a funcionalidade de marcar uma célula (flag) está funcionando;
* **Defusar uma bomba especial:** Verificar se a funcionalidade de defusar uma bomba especial quando desativada está funcionando;
* **Perder o jogo:** Verificar se o jogo acaba corretamente quando abre-se uma bomba ativa;
* **Ganhar o jogo:** Verificar se o jogo acaba corretamente quando abre todas as casas que não possuem bomba;
* **Recomeçar:** Verificar se a funcionalidade de play again funciona.

### 5.Resultado dos testes:
* **Verificação de dificuldades:** O jogo muda o tamanho do grid quando muda-se de deificuldade e mostra o scoreboard daquela dificuldade;
* **Abrir uma célula:** A célula abre mostrando se é uma bomba ou um número, e abre as células adjacentes quando o número é zero;
* **Marcar uma célula:** A célula fica marcada corretamente quando é utilizado a opção de flag;
* **Defusar uma bomba especial:** A bomba especial é defusada corretamente quando desativada e mostra que foi defusada;
* **Perder o jogo:** O jogo acaba e mostra a tela final com o scoreboard e não coloca o tempo na tabela pois o jogador perdeu;
* **Ganhar o jogo:** O jogo acaba e mostra a tela final com o scoreboard e coloca o tempo na tabela se foi menor que os 10 melhores;
* **Recomeçar:** O jogo recomeça corretamente.

### 6.Rodar o jogo:
* Baixar os arquivos do Github
* Abrir como projeto no IntelliJ a pasta com os arquivos baixados
* Rodar pelo IntelliJ o arquivo MineSweeperGUI.java
