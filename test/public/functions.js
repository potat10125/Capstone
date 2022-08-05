function HTMLItem(itemId,itemName,itemPrice,itemQuantity){
	itemPrice = moneyFormatter(itemPrice);
	
	// get html for item
	var data = 
		`<div class="col-md-4 itemButtons">
				<button type="button" class="btn btn-success snacks" id = "${itemId}" value="${itemPrice}">
		<div class = "number">
		  <h7 data-id="${itemId}Id">${itemId}</h7><br />
		</div>
		<div class = "item">
		  <h7 id = "item${itemId}">${itemName}</h7> <br />
		</div>
		<div class = "price">
		  $${itemPrice} <br /><br />
		</div>
		<div class = "quantity">
		  <h7 id="${itemName}quantity">Quantity Left: ${itemQuantity}</h7> 
		</div>
				</button>
		</div>`;
		return data;
}

//add money to total
function useMoneyButton(moneyType,moneyAmount){
	$('#' + moneyType).click(function(event){
		var numberInBoxNow = parseFloat($('#moneyBox').val());
		if(numberInBoxNow == NaN){
			numberInBoxNow = 0;
		}
		var moneyValue = moneyAmount;
		var sum = (numberInBoxNow * 10 + moneyValue * 10) / 10;
		// var z = parseFloat(bFixed);
		$('#moneyBox').val(sum);
	});
}


// load items from server
function loadItems(){
  $.ajax({
    type: 'GET',
    url: 'http://vending.us-east-1.elasticbeanstalk.com/items',

    success: function(itemsArray){
		$.each(itemsArray, function(index,item){
			console.log(item.name);
			var itemName = ''+item.name;
			var itemPrice = item.price;
			var itemQuantity = item.quantity;
			var itemId = item.id;

			$('#allItemsOnLoadUp').append(HTMLItem(itemId,itemName, itemPrice, itemQuantity));
			$('#' +itemId).click(function() {
			  selectItem(itemId, itemName);
			});

		});
    },

    error: function(){
      alert('Could not get items from server');
    }
  });
}

function selectItem(itemId,itemName){
    // put id of item in item box
    $('#hiddenItemId').val(itemId);
    $('#itemBox').val(itemId);
}

$(document).ready(function(){
	loadItems();  //load buttons

	//set values of money
	useMoneyButton('dollar',1.00);
	useMoneyButton('quarter',0.25);
	useMoneyButton('dime',0.10);
	useMoneyButton('nickel',0.05);

	$('#makePurchaseButton').click(function(event){
		var amountInserted = $('#moneyBox').val();
		var itemIdIncognito = $('#hiddenItemId').val();

		$.ajax({
		    type: 'POST',
		    url: 'http://vending.us-east-1.elasticbeanstalk.com/money/' + amountInserted + '/item/' + itemIdIncognito,
			
		    success: function(change, status){
				var allQuarters = change.quarters
				var allDimes = change.dimes
				var allNickels = change.nickels
				var allPennies = change.pennies

				var totalChange = (allQuarters + ' Quarters '  + allDimes + ' Dimes '  + allNickels + ' Nickels ' + allPennies + ' Pennies ');

				//return change
				$('#changeBox').val(totalChange);
				$('#messageBox').val('Thank You!!!');
				$('#moneyBox').val('0.00');
				$('#hiddenItemId').val("0");
				
				$('#allItemsOnLoadUp').empty(); 
				loadItems();
			},
			error: function(data){
				var errorMessages = (data.responseJSON.message);
				$('#messageBox').val(errorMessages);
			}
		})
	});



    // when the change return button is clicked
    $('#changeReturnButton').click(function(event){
		$('#moneyBox').val('');
		$('#itemBox').val('');
		$('#messageBox').val('');
		$('#changeBox').val('');

		// update items
		$('#allItemsOnLoadUp').empty();
		loadItems();
		$('#moneyBox').val(0.00);
	})
})

//force 2 decimals for money number
function moneyFormatter(moneyAmount){
	let arrayOfChars = moneyAmount.toString().split(".")[1];
	
	// if the array is undefined, 0 decimals
	if(arrayOfChars === undefined){
		return moneyAmount + ".00";
	}	

	//add enough 0's to get 2 decimals
	let sizeOfArrayOfChars = arrayOfChars.length;
	if(sizeOfArrayOfChars === 2){
		return moneyAmount;
	}else if(sizeOfArrayOfChars === 1){
		return moneyAmount + "0";
	}else{
		return moneyAmount;
	}
}
