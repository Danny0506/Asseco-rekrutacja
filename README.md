# 1. Endpoint do wyliczania ile trzeba zapłacić PLN za daną walutę po średnim kursie:

## POST http://localhost:8080/rates/average

### Przykładowy request:

```
{
    "searchDate": "2025-02-10",
    "currencies": [
        {
            "amount": "1100",
            "code": "USD"
        },
        {
            "amount": "10",
            "code": "EUR"
        }
    ]
}
```

### Przykładowy response:

```
[
    {
        "price": 4460.2800,
        "currencyName": "dolar amerykański"
    },
    {
        "price": 41.8720,
        "currencyName": "euro"
    }
]
```

# 2. Endpoint do wyświetlania kursów walut kupna i sprzedaży:

## Przykładowy URL:
## http://localhost:8080/rates/sale?searchDate=2025-02-11&codes=USD,EUR

### searchDate - parametr z datą informujący, z którego dnia zaczytujemy kursy
### codes - lista kodów walut oddzielona przecinkiem

### Przykładowy response:

```
[
    {
        "currencyName": "dolar amerykański",
        "currencyCode": "USD",
        "bid": 4.02,
        "ask": 4.10
    },
    {
        "currencyName": "euro",
        "currencyCode": "EUR",
        "bid": 4.14,
        "ask": 4.22
    }
]
```

