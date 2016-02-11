<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Adivinhando</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h3>? Adivinhe o n�mero ?</h3>
            <hr/>
            <h1>:)</h1>
            <p><b>Parab�ns</b>, voc� adivinhou o n�mero em <%= session.getAttribute(servlets.Tentar.ID_NUM_TENTATIVAS) %> tentativa(s)!</p>
            <p>Melhor Jogada: <%= session.getAttribute(servlets.Tentar.melhorJogada)%> tentativas </p>
            <a href="/adivinhando/tentarNovamente.html">Jogar Novamente</a> | <a href="/adivinhando/Sair">Sair</a>
        </div>
        <script src="js/jquery-1.12.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>