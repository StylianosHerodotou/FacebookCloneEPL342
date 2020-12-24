DROP PROCEDURE IF EXISTS search_others_Album_m;
GO

CREATE procedure search_others_Album_m(

@albumName nvarchar(50),
@userID int
)

as
begin
if @albumName !=''
select * from Picture_Album 
where Lower(Picture_Album.Name) Like (Lower(@albumName)+'%') and Picture_Album.Privacy_Name !='CLOSED' and Picture_Album.UserID!=@UserId
else
select* from Picture_Album where 1 =2
end
go



--------------------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS search_others_pic_m;
go

CREATE procedure search_others_pic_m(

@picName nvarchar(50),
@userID int

)

as
begin
if @picName !=''
select * from Picture 


where Lower(picture.SRC) Like (Lower(@picName)+'%') and picture.Privacy_Name !='CLOSED' and picture.UserID!=@UserId
else
select* from Picture_Album where 1 =2
end

go
--------------------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS search_others_vid_m;
GO

CREATE procedure search_others_vid_m(

@vidName nvarchar(50),
@userID int

)

as
begin
if @vidName !=''
select * from Video where Lower(video.Message) Like (Lower(@vidName)+'%') and video.Privacy_Name !='CLOSED' and video.UserID!=@UserId
else
select* from Picture_Album where 1 =2
end

go

--------------------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS search_others_event_m;
GO

CREATE procedure search_others_event_m(

@eventName nvarchar(50),
@userID int

)

as
begin
if @eventName !=''
select * from GATHERING where Lower(GATHERING.Name) Like (Lower(@eventName)+'%') and GATHERING.Privacy_Name !='CLOSED' and GATHERING.Creator_ID !=@UserId
else
select* from Picture_Album where 1 =2
end

go

DROP PROCEDURE IF EXISTS search_others_link_m;
GO


CREATE procedure search_others_link_m(

@linkName nvarchar(50),
@userID int

)

as
begin
if @linkName !=''
select U.* from USERS u join Link l on l.UserID = u.UserID  where Lower(l.Name) Like (Lower(@linkName)+'%')  and u.UserID!=@UserId
else
select* from Picture_Album where 1 =2
end

go

exec search_others_Album_m
@UserId = 95,
@albumName = 'A'


