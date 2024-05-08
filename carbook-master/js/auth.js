$(document).ready(function () {
    var user = JSON.parse(sessionStorage.getItem('user'));
    console.log(user);
    if (user) {
      $('#user-name').text(user.username);

      // Show the logout option when the user hovers over the user name
      $('#user-name').hover(function () {
        $('#logout-menu').css('display', 'block');
      }, function () {
        $('#logout-menu').css('display', 'none');
      });

      // Implement logout functionality
      $('#logout').click(function () {
        // Clear sessionStorage
        sessionStorage.clear();
        // Redirect to login page or perform any other action after logging out
        window.location.href = 'login.html';
      });
      if(user.userRole=="ADMIN"){
        
        $('#HOME_Home').attr('href', 'admin.html');
      }
      else{
        $('#HOME_Home').attr('href', 'index.html');
      }
    } else {
      // If user is not logged in, hide "Log Out" and redirect "Log In" to login.html
      
      $('#user-name').text('Login');
      $('#user-name').attr('href', 'login.html');
      $('#HOME_Home').attr('href', 'index.html');
      $('#logout').css('display', 'none'); // Hide the logout option
      $('#user-name').attr('href', 'login.html'); // Redirect login link to login.html
    }
  });