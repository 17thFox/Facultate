$(document).ready(function () {
	var keys = document.querySelectorAll('#calculator span');
	var operators = ['+', '-', 'x', '/'];
	var decimalAdded = false;

	for(var i = 0; i < keys.length; i++) {
		keys[i].onclick = function(e) {
			var input = document.querySelector('.screen');
			var inputVal = input.innerText;
			var btnVal = this.innerText;
			
			if(btnVal == 'C') {
				input.innerText = '';
				decimalAdded = false;
			}
			
			else if(btnVal == '=') {
				var equation = inputVal;
				var lastChar = equation[equation.length - 1];
				
				equation = equation.replace(/x/g, '*');
				
				if(operators.indexOf(lastChar) > -1 || lastChar == '.')
					equation = equation.replace(/.$/, '');
				
				if(equation)
					input.innerText = eval(equation);

				decimalAdded = false;
			}
			
			else if(operators.indexOf(btnVal) > -1) {
				var lastChar = inputVal[inputVal.length - 1];
				
				if(inputVal != '' && operators.indexOf(lastChar) == -1) 
					input.innerText += btnVal;
				
				else if(inputVal == '' && btnVal == '-') 
					input.innerText += btnVal;
				
			// Replace the last operator (if exists) with the newly pressed operator
			if(operators.indexOf(lastChar) > -1 && inputVal.length > 1) {
				input.innerText = inputVal.replace(/.$/, btnVal);
			}
			
			decimalAdded = false;
		}
		
		else if(btnVal == '.') {
			if(!decimalAdded) {
				input.innerText += btnVal;
				decimalAdded = true;
			}
		}
		
		else {
			input.innerText += btnVal;
		}
		
		// prevent page jumps
		e.preventDefault();
	} 
}
});