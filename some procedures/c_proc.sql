DROP PROCEDURE IF EXISTS search_Album_c;
GO

CREATE procedure search_Album_c(


@albumName nvarchar(50),
@userid int
)

as
begin
if @albumName !=''
select * from Picture_Album 
where Picture_Album.Name like @albumName+'%' and Picture_Album.UserID = @userid
else
select* from Picture_Album where 1 =2
end
go



--------------------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS search_pic_c;
go

CREATE procedure search_pic_c(


@picName nvarchar(50),
@userid int

)

as
begin
if @picName !=''
select * from Picture 
join Picture_in_Album on Picture.Picture_ID = Picture_in_Album.Picture_ID
join Picture_Album on Picture_Album.Picture_Album_ID = Picture_in_Album.Picture_Album_ID

where picture.SRC like @picName+'%'  and Picture_Album.UserID = @userid
else
select* from Picture_Album where 1 =2
end

go
--------------------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS search_vid_c;
GO

CREATE procedure search_vid_c(


@vidName nvarchar(50),
@userid int

)

as
begin
if @vidName !=''
select * from Video where video.Message like @vidName+'%'  and video.UserID = @userid
else
select* from Picture_Album where 1 =2
end

go
--------------------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS search_link_c;
GO

CREATE procedure search_link_c(


@linkName nvarchar(50),
@userid int
)

as
begin
if @linkName !=''
select * from Link where Link.Name like @linkName+'%'  and Link.UserID= @userid
else
select* from Picture_Album where 1 =2
end

go
--------------------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS search_event_c;
GO

CREATE procedure search_event_c(


@eventName nvarchar(50),
@userid int

)

as
begin
if @eventName !=''
select * from GATHERING where GATHERING.Name like  @eventName+'%'  and GATHERING.Creator_ID = @userid
else
select* from Picture_Album where 1 =2
end

go

exec search_Album_c
@UserId = 7,
@albumName = 'A'
