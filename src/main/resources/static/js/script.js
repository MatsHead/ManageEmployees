const id_text_fail_ajax = 'afterDeleteText-fail', id_text_success_ajax = 'afterDeleteText-success';
const contentType = 'application/json';
const employeeApiUrl = 'http://localhost:8080/api/employees/';
const departmentApiUrl = 'http://localhost:8080/api/departments/';

// functions For modal edit
function callEditModal(){
    toggleModalWithId('modalEdit');
}

function modalEditContinue() {
    setText(id_text_fail_ajax, '');
    setText(id_text_success_ajax, '');
    let providedId = $('#employeeId-edit').val();
    if (!isNaN(providedId)) {
        window.location.replace(employeeApiUrl+ providedId + '/edit');
    } else {
        setText(id_text_fail_ajax, 'Please provide a number in modal view.');
        toggleModalWithId('modalEdit');
    }

}
// functions for modal show
function callShowModal(){
    toggleModalWithId('modal-show-one');
}
function modalShowContinue() {
    setText(id_text_fail_ajax, '');
    setText(id_text_success_ajax, '');
    var providedId = $('#employeeId-show-one').val();
    if (!isNaN(providedId)) {
        window.location.replace(employeeApiUrl + providedId + '/show-one');
    } else {
        setText(id_text_fail_ajax, 'Please provide a number in modal view.');
        toggleModalWithId('modal-show-one');
    }

}
//functions for delete modal
function callDeleteModal(){
    toggleModalWithId('modal-delete');
}
function modalDeleteContinue() {
    let providedId = $('#employeeId-delete').val();
    setText(id_text_fail_ajax, '');
    setText(id_text_success_ajax, '');
    if (!isNaN(providedId)){
        $.ajax({
            type: 'DELETE',
            url: employeeApiUrl + providedId + '/delete',
            contentType: contentType,
            success: function(result) {
                deleteRowInTableById(providedId);
                setText(id_text_success_ajax, "Employee with id: " + providedId + " has been deleted successfully.");
                setText(id_text_fail_ajax, "");
                toggleModalWithId('modal-delete');

            },error: function(){
                setText(id_text_fail_ajax, "Employee with id: " + providedId + " has NOT been deleted successfully." +
                    " Check if this employee exists.")
                toggleModalWithId('modal-delete');
            }
        });
    } else {
        toggleModalWithId('modal-delete');
        setText(id_text_fail_ajax, 'Please provide a number in modal view.')
    }
}
function deleteDepartment(id){
    $.ajax({
        type: 'DELETE',
        url: departmentApiUrl + id + '/delete',
        contentType: contentType,
        success: function(result) {
            deleteRowInTableById(id);
            setText(id_text_success_ajax, "Department with id: " + id + " has been deleted successfully.");
            setText(id_text_fail_ajax, "");
            toggleModalWithId('modal-delete');

        },error: function(){
            setText(id_text_fail_ajax, "Department with id: " + id + " has NOT been deleted successfully." +
                " Check if this department exists.")
            toggleModalWithId('modal-delete');
        }
    });
}
function deleteDepartment(id){
    $.ajax({
        type: 'DELETE',
        url: departmentApiUrl + id + '/delete',
        contentType: contentType,
        success: function(result) {
            deleteRowInTableById(id);
            setText(id_text_success_ajax, "Department with id: " + id + " has been deleted successfully.");
            setText(id_text_fail_ajax, "");
            toggleModalWithId('modal-delete');

        },error: function(){
            setText(id_text_fail_ajax, "Department with id: " + id + " has NOT been deleted successfully." +
                " Check if this department exists.")
            toggleModalWithId('modal-delete');
        }
    });
}
function exportEmployees(){
    $.ajax({
        type: 'POST',
        url: employeeApiUrl + 'export',
        contentType: contentType,
        success: function(result) {
            setText(id_text_fail_ajax, "");
            setText(id_text_success_ajax, "Employees have been exported to file: ");

        },error: function(){
            setText(id_text_success_ajax, "");
            setText(id_text_fail_ajax, "Employees have not been exported, please check if you chose a proper file with xls extension");
        }
    });
}

//Toggle modal
function toggleModalWithId (id){
    $('#'+id).modal('toggle');
}

//Setting text in element
function setText(idOfElement, text) {
    $('#'+idOfElement).text(text)
}
//Delete row with id
function deleteRowInTableById(id) {
    $('tr#' + id).remove();
}