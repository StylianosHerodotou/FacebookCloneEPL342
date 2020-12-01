
DECLARE @fname nvarchar(50)= 'Allan';
DECLARE @lname nvarchar(50) = '';
DECLARE	@username nvarchar(25)= '';
DECLARE	@email nvarchar(255)= '';
DECLARE	@website nvarchar(1000)= '';
DECLARE	@link nvarchar(1000)= 'JuliusNortham1';
DECLARE @birthday date =DATEFROMPARTS (1982, 6, 8);
DECLARE	@gender bit= NULL;
DECLARE	@workedFor StringTablE ;
INSERT INTO @workedFor VALUES ('u1 edu')
DECLARE	@educationPlaces StringTablE ;
INSERT INTO @educationPlaces VALUES ('TEST2')
DECLARE	@quotes StringTablE ;
INSERT INTO @quotes VALUES ('TEST3')
DECLARE	@isVerified bit= NULL;
DECLARE	@hometown int = 101;
DECLARE	@livesInLocation int = 22;

DECLARE @BOOL bit = 0;
DECLARE @SCRIPT NVARCHAR(MAX) = 'SELECT  * 
FROM USERS AS U
WHERE  1=2 '

IF @fname != '' 
BEGIN 
SET @SCRIPT = @SCRIPT + ' or u.First_Name = ''' + @fname + '''' + CHAR(13)
SET @BOOL = 1;
END

IF @lname != '' 
BEGIN
SET @SCRIPT = @SCRIPT + ' or u.Last_Name = ''' + @lname + ''''+ CHAR(13)
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
return
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



--IF( EXISTS(SELECT 1 FROM @workedFor))
--begin
--SET @SCRIPT2 = @SCRIPT2 + ' or UW.Workplace in (SELECT * FROM  ' + @workedFor  + CHAR(13)
--SET @BOOL = 1;
--end

--IF(NOT EXISTS(SELECT 1 FROM @educationPlaces))
--begin
--SET @SCRIPT2 = @SCRIPT2 + ' or UE.Education_Place in (SELECT * FROM  @educationPlaces)'  + CHAR(13)
--SET @BOOL = 1;
--end

--IF(NOT EXISTS(SELECT 1 FROM @quotes))
--begin
--SET @SCRIPT2 = @SCRIPT2 + ' or UQ.Quote in (SELECT * FROM  @quotes)'  + CHAR(13)
--SET @BOOL = 1;
--end

--PRINT(@SCRIPT2)

--IF (@BOOL = 1)
--BEGIN
--EXEC(@SCRIPT2)

--END
--else

--begin
--select * from USERS where 1 =2 
--return
--end




