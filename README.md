# ATNM

Demo App 

<img src="https://github.com/IuliuCristianDumitrache/ExchangeAppCleanArchitecture/blob/master/video-1654683000%20(1).gif" width="400" height="790">

Technical specifications

- Clean MVVM architecture 
- Design follow Material design guidelines
- Jetpack Components used: data binding, livedata, navigation, viewmodel
- Flows
- Coroutines
- Dependency injection with Hilt
- Retrofit



The requirements of the app is the following:
- Implement a single screen where you display a list of currency exchange pairs.
- The structure of the UI should be the following: a list of exchange pairs, each element
should contain to and from currencies and their corresponding exchange rate.
- The UI should be minimal but it should adhere to the Material Design guidelines.
Technical Notes:
- The list of currency exchange pairs are obtained from consuming a REST API with
the following endpoint:
http://gnb.dev.airtouchmedia.com/rates2.json
From the response of the GET request you will have to populate the list with the pairs
from the “pairs” array. Each pair contains a “from” currency and a ”to” currency.
- In order to calculate the conversion rate for each pair, you will need to use the rates
from the rates array.
- Conversion rates are sometimes indirect and you have to calculate them
programmatically.
A direct example would be if a pair from the “pairs” array is:
{
"from": "USD",
"to": "AUD"
},
To get its rates, you can directly find the corresponding rate from the “rates” array:
{
"from": "USD",
"to": "AUD",
"rate": "1.96"
}
An indirect example would be if a pair from the “pairs” array is:
{
"from": "USD",
"to": "AUD"
},
But no corresponding rate is found in the “rates” array. Instead, the “rates” array
contains these two intermediary rates:
{
"from": "USD",
"to": "EUR",
"rate": "3.96"
}
And:�
{
"from": "EUR",
"to": "AUD",
"rate": "1.96"
}
To calculate the USD to AUD rate, we must multiply the USD-EUR rate with the
EUR-AUD rate.
Please note that some indirect rates can have more than 2 intermediary rates, so try
to make your logic flexible.
