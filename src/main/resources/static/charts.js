function generateLineChart(dates, datesValues) {
    fetch('/chart/entryline')
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка при выполнении запроса: ' + response.statusText);
            }
            return response.json();
        })
        .then(responseObject => {
            let dates;
            let counterValues;
            for (let i = 0; i < responseObject.length; i++) {
                dates[i] = responseObject.date[i];
                counterValues[i] = responseObject.counter[i];
            }
            let chart = new Chart(document.getElementById("line-chart"), {
                type: 'line',
                data: {
                    labels: responseObject.dates,
                    // labels: ["2023-11-17","2023-11-18"],
                    datasets: [{
                        data: responseObject.values,
                        // data: [1,2],
                        label: "Количество переходов",
                        borderColor: "#8e5ea2",
                        fill: false
                    }]
                },
                options: {
                    title: {
                        display: true,
                        text: 'Количество переходов по пригласительной ссылке',
                        fontSize: 20
                    },
                    legend: {
                        labels: {
                            fontSize: 16 // Установка размера шрифта легенды
                        }
                    },
                    scales: {
                        yAxes: [{
                            ticks: {
                                fontSize: 16,
                                beginAtZero: true,
                                precision: 0, // Отображение только целочисленных значений
                                stepSize: 1,
                                callback: function (value, index, values) {
                                    return Math.round(value); // Округление значения
                                }
                            }
                        }],
                        xAxes: [{
                            ticks: {
                                fontSize: 16
                            }
                        }]
                    },
                    maintainAspectRatio: false
                }
            });
            console.log(responseObject);
        })
        .catch(error => {
            console.error('Произошла ошибка:', error);
        });

}


