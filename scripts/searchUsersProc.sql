CREATE procedure SEARCH_USERS_TEMP2(

	@firstName nvarchar(50),
	@lastName nvarchar(50),
	@username nvarchar(50),
	@email nvarchar(500),
	@website nvarchar(500),
	@link nvarchar(500),
	@birthday Date,
	@gender bit,
	@workedFor table READONLY,
	@educationPlaces StringTablE READONLY,
	@quotes StringTablE READONLY,
	@isVerified bit,
	@hometown nvarchar(50),
	@livesInLocation nvarchar(50)
	)
AS
BEGIN

DECLARE @BOOL bit = 0;
DECLARE @SCRIPT NVARCHAR(MAX) = 'SELECT * 
FROM USERS AS U
WHERE 1=2 '

IF @firstName != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.First_Name = ''' + @firstName + '''' + CHAR(13)
SET @BOOL = 1;
END

IF @lastName != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.Last_Name = ''' + @lastName + ''+ CHAR(13)
SET @BOOL = 1;
END

IF	@username != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.[Username] = ''' + @username + ''''+ CHAR(13)
SET @BOOL = 1;
END

IF	@email != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.[Email] = ''' + @email + ''''+ CHAR(13)
SET @BOOL = 1;
END

IF	@website != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.Website = ''' + @website + ''' '+ CHAR(13)
SET @BOOL = 1;
END

IF	@link != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.[Link] = ''' + @link + ''''+ CHAR(13)
SET @BOOL = 1;
END

IF @birthday is not NULL 
BEGIN
SET @SCRIPT = @SCRIPT + ' or datediff(day,u.BIRTHDAY, Cast(''' + CONVERT(VARCHAR(10),@birthday,23) + ''' as date)) =0 '+ CHAR(13)
SET @BOOL = 1;
END

IF	@gender is not NULL
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.[Gender] = ' + CONVERT(varchar(10),@gender)+ CHAR(13)
SET @BOOL = 1;
END

IF	@isVerified is not NULL 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.[Is_verified] = ' + CONVERT(varchar(10),@isVerified)+ CHAR(13)
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


IF OBJECT_ID('TEMP', 'U') IS NOT NULL DROP TABLE TEMP;

CREATE TABLE TEMP(
	[UserID] [int] NOT NULL,
	[Username] [varchar](50) NOT NULL,
	[Pass] [varchar](50) NOT NULL,
	[First_Name] [varchar](50) NOT NULL,
	[Last_Name] [varchar](50) NOT NULL,
	[Email] [varchar](255) NOT NULL,
	[Website] [varchar](1000) NOT NULL,
	[Link] [varchar](1000) NOT NULL,
	[Birthday] [date] NOT NULL,
	[Gender] [bit] NOT NULL,
	[Is_verified] [bit] NOT NULL,
	[Hometown_LOC_ID] [int] NOT NULL,
	[Current_LOC_ID] [int] NOT NULL,
)



IF (@BOOL = 1)
BEGIN
Insert into TEMP
EXEC(@SCRIPT)
SELECT * FROM TEMP
END
else
begin
select * from USERS where 1 =2 
return
end

set @BOOL = 0

DECLARE @SCRIPT2 NVARCHAR(MAX) = '
SELECT T.*
FROM TEMP AS T
LEFT OUTER JOIN User_Work AS UW ON T.UserID = UW.UserID
LEFT OUTER JOIN User_Education AS UE ON T.UserID = UE.UserID 
LEFT OUTER JOIN User_Quotes AS UQ ON T.UserID = UQ.UserID
WHERE 1=1 '



IF(NOT EXISTS(SELECT 1 FROM @workedFor))
begin
SET @SCRIPT2 = @SCRIPT2 + ' or UW.Workplace in (SELECT * FROM   @workedFor) '  + CHAR(13)
SET @BOOL = 1;
end

IF(NOT EXISTS(SELECT 1 FROM @educationPlaces))
begin
SET @SCRIPT2 = @SCRIPT2 + ' or UE.Education_Place in (SELECT * FROM  @educationPlaces)'  + CHAR(13)
SET @BOOL = 1;
end

IF(NOT EXISTS(SELECT 1 FROM @quotes))
begin
SET @SCRIPT2 = @SCRIPT2 + ' or UQ.Quote in (SELECT * FROM  @quotes)'  + CHAR(13)
SET @BOOL = 1;
end

PRINT(@SCRIPT2)

IF (@BOOL = 1)
BEGIN
EXEC(@SCRIPT2)

END
else

begin
select * from USERS where 1 =2 
return
end


END

CREATE stringtable work(
);
iNSERT INTO work VALUES ('u1 work');

CREATE TABLE edu(
	string nvarchar(50)
);
iNSERT INTO edu VALUES ('u1 edu');

CREATE TABLE quo(
	string nvarchar(50)
);
iNSERT INTO quo VALUES ('u1 quo');



EXEC SEARCH_USERS_TEMP2
	@firstname = 'Allan',
	@lastName  = '',
	@username = '',
	@email = '',
	@website= '',
	@link = 'JuliusNortham1',
	@birthday = NULL,
	@gender= NULL,
	@workedFor = work ,
	@educationPlaces = edu ,
	@quotes = quo ,
	@isVerified = NULL,
	@hometown  = 101,
	@livesInLocation  = 22;