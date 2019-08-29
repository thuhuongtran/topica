$(document).ready(function () {
  $("#modalRegister").modal({
    backdrop: 'static',
    keyboard: false
  });
  $("#formRegister").bootstrapValidator({
    feedbackIcons: {
      valid: 'glyphicon glyphicon-ok',
      invalid: 'glyphicon glyphicon-remove',
      validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
      name: {
        validators: {
          notEmpty: {
            message: 'The full name is required'
          },
          stringLength: {
            min: 6,
            max: 50,
            message: 'The full name must be at least 6 characters and less than 50 characters'
          }
        }
      },
      uname: {
        validators: {
          notEmpty: {
            message: 'The username is required'
          },
          different: {
            field: 'name',
            message: 'The username cannot be the same as the full name'
          },
          stringLength: {
            min: 6,
            max: 30,
            message: 'The full name must be at least 6 characters and less than 30 characters'
          }
        }
      },
      pwd: {
        validators: {
          notEmpty: {
            message: 'The password is required'
          },
          regexp: {
            regexp: '[a-zA-Z0-9].*[!@#$%^&*(),.?":{}|<>].*',
            message: 'The password must contains alphabets, at least one special character and at least one number.'
          },
          stringLength: {
            min: 9,
            max: 20,
            message: 'The full name must be at least 9 characters and less than 20 characters'
          }
        }
      }
    },
  });
});