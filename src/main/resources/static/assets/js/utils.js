function isEmpty(data) {
    return data == null || data.length === 0;
}

function getDateAndTime(today) {
    let date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    return date + ' ' + time;
}