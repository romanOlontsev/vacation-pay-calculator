# Vacation pay calculator

Test task for [NeoFlex](https://edu.neoflex.ru/).

<p> The application accepts your average salary for 12 months and the number of vacation days - it answers with the amount of vacation pay that the employee will receive. </p>
<p> When requesting, you can also specify the exact day of going on vacation, then the calculation of vacation pay is carried out taking into account holidays. </p>

To calculate use `GET` `/calculate` with params:
* averageSalary - average salary for 12 months
* vacationDaysCount - number of vacation days
* vacationStart (optional) - the day you go on vacation in the format yyyy-mm-dd

[Enrico Service](https://kayaposoft.com/enrico/) is used to define holidays.