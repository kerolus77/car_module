$(document).ready(function() {
    console.log("hi");

    $.ajax({
      url: 'http://localhost:8761/eureka/api/transactions/getAllTransactions',
      type: 'GET',
      dataType: 'json',
      success: function(data) {
        // Handle the data returned from the server
        console.log(data);
      },
      error: function(xhr, status, error) {
        // Handle errors
        console.error('Error:', error);
      }
    });
  });

  