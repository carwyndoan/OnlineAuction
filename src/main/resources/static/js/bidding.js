// A $( document ).ready() block.
$(document).ready(function() {
    $("#btnBid").click(function() {
        let data = {
            "bidding_id": $("#bidding_id").val(),
            "user_id": $("#user_id").val(),
            "bid_amount": $("#bid_value").val()
        }
        placeBid(JSON.stringify(data));
    });

    $("#btnDeposit").click(function() {
        let data = {
            "bidding_id": $("#bidding_id").val(),
            "user_id": $("#user_id").val()
        }
        deposit(JSON.stringify(data));
    });
});

function deposit(data) {
    const url = "http://localhost:8080/bid/deposit";
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        contentType: "application/json",
        dataType: "json",
        success: paypalDeposit,
        error: showErrors
    });
}

function paypalDeposit(result) {
    console.log(result);
    // $("#lnkPaypal").attr("href").empty().append(result.approval_link);
    // make_visible("lnkPaypal");
    window.location.href = result.approval_link;
}

function placeBid(data) {
    const url = "http://localhost:8080/bid/placebid";
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        contentType: "application/json",
        dataType: "json",
        success: updateBid,
        error: showErrors
    });
}

function updateBid(result) {
    console.log(result);
    $("#total_bidder").empty().append(result.total_bidder + " bids");
    $("#counter").empty().append(result.counterDueDate);
    $("#current_bid").empty().append(result.current_bid);
    $("#bid_amount").empty().append(result.bid_amount);
    $("#bid_value").value = "";
    $("#errors").empty();
    make_hidden("errors");
}

function showErrors(XMLHttpRequest, textStatus, errorThrown) {
    make_visible('errors');
    $("#errors").empty();
    let errorObject = XMLHttpRequest.responseJSON;
    console.log(errorObject);
    let errorMsg = '<h3> Error(s)!! </h3>';
    if (errorObject.errorType === "ValidationError") {
        errorMsg += "<p>";
        var errorList = errorObject.errors;
        $.each(errorList, function(i, error) {
            errorMsg = errorMsg + error.message + '<br>';
        });
        errorMsg += '</p>';
    } else {
        errorMsg += errorObject.message; // "non" Validation
    }
    $('#errors').append(errorMsg);
    $('#errors').show();
}

make_hidden = function(id) {
    var element = document.getElementById(id);
    element.style.display = 'none';
}

make_visible = function(id) {
    var element = document.getElementById(id);
    element.style.display = 'block';
}