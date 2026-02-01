console.log("contact.js");


const baseURL="http://scm002.ap-south-1.elasticbeanstalk.com"
//const baseURL="http://localhost:8081";

var contactModal; // ✅ GLOBAL (important)

document.addEventListener("DOMContentLoaded", function () {

    const viewContactModal =
        document.getElementById("view_contact_modal");

    const option = {
        placement: 'bottom-right',
        backdrop: 'dynamic',
        backdropClasses:
            'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
        closable: true,
        onHide: () => console.log('modal is hidden'),
        onShow: () => console.log('modal is shown'),
        onToggle: () => console.log('modal has been toggled'),
    };

    const instanceOption = {
        id: 'view_contact_modal',
        override: true,
    };

    contactModal = new Modal(
        viewContactModal,
        option,
        instanceOption
    );
});

function openContactModal() {
    contactModal.show();
}

function closeContactModal(){
    contactModal.hide();
}

async function loadContactdata(id){

    //function use to load data
    console.log(id);

    try{
       const data= await (await fetch(`${baseURL}/api/contacts/${id}`)
       ).json();
       console.log(data);
       console.log(data.name);

       document.querySelector("#contact_name").innerHTML = data.name;
       document.querySelector("#contact_email").innerHTML = data.email;
       document.querySelector("#contact_image").src = data.picture;
       document.querySelector("#contact_address").innerHTML = data.address;
       document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
       document.querySelector("#contact_about").innerHTML = data.description;
       const contactFavorite = document.querySelector("#contact_favorite");
       if (data.favorite) {
         contactFavorite.innerHTML =
          "<i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i>";
        } else {
          contactFavorite.innerHTML = "Not Favorite Contact";
        }

       document.querySelector("#contact_website").href = data.websiteLink;
       document.querySelector("#contact_website").innerHTML = data.websiteLink;
       document.querySelector("#contact_linkedIn").href = data.linkedInLink;
       document.querySelector("#contact_linkedIn").innerHTML = data.linkedInLink;
       openContactModal();

    }catch(error) {
        console.log("error",error);

    }



}

//delete contact

async function deleteContact(id){

    const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
        confirmButton: "btn btn-success",
        cancelButton: "btn btn-danger"
    },
    buttonsStyling: true
    });
    swalWithBootstrapButtons.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Yes, delete it!",
    cancelButtonText: "No, cancel!",
    reverseButtons: true
    }).then((result) => {
    if (result.isConfirmed) {

        const url=`${baseURL}/user/contacts/delete/` + id;
        window.location.replace(url);
        
    } 
    });
        
}