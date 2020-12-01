BULK INSERT LOC
FROM 'D:\User\Desktop\FacebookClone\cities.txt'
WITH
(
FIRSTROW = 2,
FIELDTERMINATOR= '\t',
ROWTERMINATOR = '\n'

)