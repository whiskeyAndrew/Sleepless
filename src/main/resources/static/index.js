let buttonListContainer
let buttonListHtml
window.onload = function () {
    buttonListContainer = document.getElementById('buttonList');
    buttonListHtml = buttonListContainer.innerHTML;
    buttonListContainer.innerHTML = '';
    buttonListContainer.style.display = 'none';
}

function onShowDownloadsListPressed() {

    if (buttonListContainer.style.display === 'block') {
        buttonListContainer.innerHTML = '';
        buttonListContainer.style.display = 'none';
    } else {

        buttonListContainer.innerHTML = buttonListHtml;
        buttonListContainer.style.display = 'block';
    }
}
