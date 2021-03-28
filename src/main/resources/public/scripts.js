$(function() {
    // Auto-focus on first form input:
    $('#templateAndModelForm *:input[type!=hidden]:first').focus();

    // Dynamically adapt text areas heights to their content:
    autosize($('textarea'));

    $('#eval-btn').click(function() {
        // Request payload
        var payload = {
            template: $('#template').val()
        }

        // Parameters
        var paramText = $('#dataModel').val()
        if(paramText.length > 0) {
            var paramPairs = paramText.split('\n')
            var parameters = {}

            for(index = 0; index < paramPairs.length; index++) {
            	if(paramPairs[index].includes('=')) {
            		var parameter = paramPairs[index].split('=')
                    parameters[parameter[0].trim()] = parameter[1].trim()
            	}
            }
            payload.parameters = parameters
        }

        $.ajax({
            url: '/api/expandTemplate',
            method: 'POST',
            contentType: 'application/json',
            dataType: 'text',
            data: JSON.stringify(payload),
            success: function(response) {
                $('#result').val(response);
                $('.resultContainer').show();
                autosize.update($("#result"));
            }
        });
    });
});