$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
});

var test = document.getElementById("map");


function confirmdelete(url){
  let result = confirm("Voulez-vous vraiment supprimer ce club ?");
  if(result){
    fetch(url)
        .then(function(response) {
            setTimeout(function(){
                window.location.replace("/admin");
            }, 1000)
            alert("Le club a bien été suprimé");
        });
    }
}


