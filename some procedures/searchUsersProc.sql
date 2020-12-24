DROP PROCEDURE IF EXISTS SEARCH_USERS_TEMP2;
GO


CREATE procedure SEARCH_USERS_TEMP2(

	@firstName nvarchar(50),
	@lastName nvarchar(50),
	@username nvarchar(50),
	@email nvarchar(500),
	@website nvarchar(500),
	@link nvarchar(500),
	@birthday Date,
	@workedFor nvarchar(1000),
	@educationPlaces nvarchar(1000),
	@quotes nvarchar(1000),
	@hometown nvarchar(50),
	@livesInLocation nvarchar(50)
	)
AS
BEGIN



DECLARE @BOOL bit = 0;
DECLARE @SCRIPT NVARCHAR(MAX) = 
'SELECT distinct U.*
FROM USERS AS U
left outer JOIN User_Work AS UW ON u.UserID = UW.UserID
left outer JOIN User_Education AS UE ON u.UserID = UE.UserID 
left outer JOIN User_Quotes AS UQ ON u.UserID = UQ.UserID
WHERE 1=2 '

IF @workedFor != '' 
SET @SCRIPT = @SCRIPT + 'or (EXISTS(SELECT 1 FROM  STRING_SPLIT(''' + @workedFor + ''', ''&'')) and UW.Workplace in (SELECT * FROM STRING_SPLIT(''' + @workedFor + ''', ''&'')))' + CHAR(13)

IF @educationPlaces != '' 
SET @SCRIPT = @SCRIPT + ' or (EXISTS(SELECT 1 FROM  STRING_SPLIT(''' + @educationPlaces + ''', ''&'')) and Ue.Education_Place in (SELECT * FROM STRING_SPLIT(''' + @educationPlaces + ''', ''&''))) ' + CHAR(13)

IF @quotes != '' 
SET @SCRIPT = @SCRIPT + 'or (EXISTS(SELECT 1 FROM  STRING_SPLIT(''' + @quotes + ''', ''&'')) and Uq.Quote in (SELECT * FROM STRING_SPLIT(''' + @quotes + ''', ''&''))) ' + CHAR(13)



IF @firstName != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.First_Name like  ''' + @firstName + ''' + ''%'' ' + CHAR(13)
SET @BOOL = 1;
END



IF @lastName != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.Last_Name like ''' + @lastName + '''+ ''%'' '+ CHAR(13)
SET @BOOL = 1;
END

IF	@username != '' 
BEGIn
SET @SCRIPT = @SCRIPT + ' or u.Username like ''' + @username + '''+ ''%'' '+ CHAR(13)
SET @BOOL = 1;
END

IF	@email != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.[Email] like ''' + @email + '''+ ''%'' '+ CHAR(13)
SET @BOOL = 1;
END

IF	@website != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.Website like ''' + @website + '''+ ''%'' '+ CHAR(13)
SET @BOOL = 1;
END

IF	@link != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.[Link] like ''' + @link + ''''+ CHAR(13)
SET @BOOL = 1;
END

IF @birthday is not NULL 
BEGIN
SET @SCRIPT = @SCRIPT + ' or datediff(day,u.BIRTHDAY, Cast(''' + CONVERT(VARCHAR(10),@birthday,23) + ''' as date)) =0 '+ CHAR(13)
SET @BOOL = 1;
END


IF @hometown != -1 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.[Hometown_LOC_ID] = ' + CONVERT(varchar(10),@hometown)+ CHAR(13)
SET @BOOL = 1;
END

IF @livesInLocation != -1 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.Current_LOC_ID = ' + CONVERT(varchar(10),@livesInLocation)
SET @BOOL = 1;
END


exec (@script)




END
go



--delete from User_Work where USERID in (1,2)
--insert into User_Work values ('u1 work',1),('u2 work',2)

--delete from User_Education where USERID = 2
--insert into User_Education values ('u2 edu',2)

--delete from User_Work where USERID = 3
--insert into User_Work values ('u3 quo',3)


--EXEC SEARCH_USERS_TEMP2
--	@firstname = '',
--	@lastName  = '',
--	@username = '',
--	@email = '',
--	@website= '',
--	@link = 'PetrosBracey6',
--	@birthday = NULL,
--	@workedFor = 'u1 work&u2 work',
--	@educationPlaces = '',
--	@quotes = 'u3 quo' ,
--	@hometown  = 68,
--	@livesInLocation  = 74;
--go


EXEC SEARCH_USERS_TEMP2
	@firstname = 'Stylianos',
	@lastName  = '',
	@username = '',
	@email = '',
	@website= '',
	@link = '',
	@birthday = NULL,
	@workedFor = '',
	@educationPlaces ='',
	@quotes = '' ,
	@hometown  = -1,
	@livesInLocation  = -1;
