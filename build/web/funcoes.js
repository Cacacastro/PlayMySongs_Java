function GravaDadosFoto()
{
   event.preventDefault(); // evita refresh da tela
                
   const URL_TO_FETCH = 'RecebeDados';

   var formData = new FormData(document.getElementById("form"));
   //formData.append('acao', 'confirmar'); opcional, caso queira inserir outra informação
                
   fetch(URL_TO_FETCH, { method: 'post',body: formData 
   }).then(function (response) {
        return response.text();
   }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico
                  
        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('resultado').innerHTML = retorno;
            //document.getElementById('erro').style.display = "block";
        } else  // tudo OK, limpar o formulário
        {
            document.getElementById('form').reset(); 
            document.getElementById('resultado').innerHTML = retorno;
        }
   }).catch(function (error) {
        console.error(error);
   });
}

function mostraMusica(myform)
{
    event.preventDefault(); //evitar a submissão/reload da página    
   
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("post","./ServletGeraMusica");
    var formData = new FormData(myform); //para recuperar os parâmetros do form
    const data = new URLSearchParams();

    for (const pair of formData)   //inserindo os parâmetros individualmente
        data.append(pair[0], pair[1]);
    
    httpRequest.send(data); // enviando os parâmetros junto com a chamada do servlet
    httpRequest.onreadystatechange = function () 
    {
        if (httpRequest.readyState === 4 && httpRequest.status === 200)
        {
            document.getElementById("resultado").innerHTML = httpRequest.responseText;
            //vet = httpRequest.responseText.split("#");
            //if(vet[0] != null)
            //    document.getElementById("mus1").src=innerHTML=vet[0];
            //if(vet[1] != null)
            //    document.getElementById("mus2").src=innerHTML=vet[1];
            //if(vet[2] != null)
            //    document.getElementById("mus3").innerHTML=vet[2];
            //if(vet[3] != null)
            //    document.getElementById("mus4").innerHTML=vet[3];
            //if(vet[4] != null)
            //    document.getElementById("mus5").innerHTML=vet[4];
            //if(vet[5] != null)
            //    document.getElementById("mus6").innerHTML=vet[5];
            //if(vet[6] != null)
            //    document.getElementById("mus7").innerHTML=vet[6];
            //if(vet[7] != null)
            //   document.getElementById("mus8").innerHTML=vet[7];
            //if(vet[8] != null)
            //   document.getElementById("mus9").innerHTML=vet[8];
            //if(vet[9] != null)
            //    document.getElementById("mus10").innerHTML=vet[9];
        }
        
    };   
}
