printf "\e[33mSend the date and the time of the movie\n\e[0m"
printf "\e[33mRequest:\e[0m http://localhost:8080/screenings/?startTime=2019-05-25T10:00&endTime=2019-05-25T19:00\n"
printf "\e[33mResponse:\e[0m "
curl "http://localhost:8080/screenings/?startTime=2019-05-25T10:00&endTime=2019-05-25T19:00"
printf "\n\n"

printf "\e[33mChoose the screening\n\e[0m"
printf "\e[33mRequest:\e[0m http://localhost:8080/screenings/0\n"
printf "\e[33mResponse:\e[0m "
curl "http://localhost:8080/screenings/0"
printf "\n\n"

printf "\e[33mReserve seats\n\e[0m"
printf "\e[33mRequest:\e[0m http://localhost:8080/reservations/\n"
printf '\e[33mData:\e[0m
{
    "screeningId":0,
    "firstName":"Dawid",
    "lastName":"Biadun",
    "reservedSeats":[
        {
           "row":3,
           "column":10,
           "ticketType":"ADULT"
        },
        {
           "row":3,
           "column":11,
           "ticketType":"CHILD"
        },
        {
           "row":3,
           "column":12,
           "ticketType":"CHILD"
        },
        {
           "row":3,
           "column":13,
           "ticketType":"ADULT"
        }

    ]
}\n'
printf "\e[33mResponse:\e[0m"
curl -X POST \
    http://localhost:8080/reservations/ \
    -H 'content-type: application/json' \
    -d '{
        "screeningId":0,
        "firstName":"Dawid",
        "lastName":"Biadun",
        "reservedSeats":[
            {
                "row":3,
                "column":10,
                "ticketType":"ADULT"
            },
            {
                "row":3,
                "column":11,
                "ticketType":"CHILD"
            },
            {
                "row":3,
                "column":12,
                "ticketType":"CHILD"
            },
            {
                "row":3,
                "column":13,
                "ticketType":"ADULT"
            }
        ]
    }'

printf "\n"