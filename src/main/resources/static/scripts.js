const generalUrl = "./exchangerates/api";

function loadGif() {
    let code = $("#currency_codes").val();
    $.ajax({
        url: generalUrl + "/gif/" + code,
        method: "GET",
        dataType: "json",
        complete: function (resp) {
            let content = JSON.parse(resp.responseText);
            let image = document.createElement("img");
            image.src = content.data.images.original.url;
            let gifTitle = document.createElement("p");
            gifTitle.textContent = content.data.title;
            let resultMsg = document.createElement("p");
            resultMsg.textContent = content.resultMessage;

            let gifContainer = document.querySelector("#gif");
            gifContainer.innerHTML = "";
            gifContainer.insertAdjacentElement("afterbegin", resultMsg);
            gifContainer.insertAdjacentElement("afterbegin", image);
            gifContainer.insertAdjacentElement("afterbegin", gifTitle);
        }
    })
}

function loadCurrencyForSelect() {
    $.ajax({
        url: generalUrl + "/getcurrencycodes",
        method: "GET",
        complete: function (resp) {
            let currencyCodes = JSON.parse(resp.responseText);
            let select = document.querySelector("#currency_codes");
            for(let i = 0; i < currencyCodes.length; i++) {
                let option = document.createElement("option");
                option.value = currencyCodes[i];
                option.text = currencyCodes[i];
                select.insertAdjacentElement("beforeend", option);
            }
        }
    })
}