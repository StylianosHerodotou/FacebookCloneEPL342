CREATE PROC getFullUsers 
AS
BEGIN
SELECT * 
FROM USERS AS U 
 LEFT OUTER  JOIN User_Work AS UW ON U.UserID = UW.UserID
 LEFT OUTER  JOIN User_Education AS UE ON U.UserID = UE.UserID
 LEFT OUTER  JOIN User_Quotes AS UQ ON U.UserID = UQ.UserID

ORDER BY  U.UserID

END