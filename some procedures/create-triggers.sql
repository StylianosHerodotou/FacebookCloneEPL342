
GO
DROP TRIGGER IF EXISTS event_log_trig
GO

CREATE TRIGGER event_log_trig
on gathering
AFTER INSERT,UPDATE,DELETE
AS
BEGIN
SET NOCOUNT ON;
if exists(SELECT 1 from inserted) and exists (SELECT * from deleted)
begin
	
  --(@a,'UPDATE','friend_request', GETDATE() ,  inserted.User_Who_Sent_request_ID from inserted )
    INSERT into logs  
		SELECT 
         I.GATHERING_ID,I.Name,'insert' , 'event', GETDATE() ,  I.Creator_ID
		FROM inserted I
end

If exists (Select 1 from inserted) and not exists(Select * from deleted)
begin
   
          INSERT into logs  
		SELECT 
        I.GATHERING_ID,I.Name,'update' , 'event', GETDATE() ,  I.Creator_ID
		FROM inserted I
end

If exists(select 1 from deleted) and not exists(Select * from inserted)
begin 
  
          INSERT into logs  
		SELECT 
         I.GATHERING_ID,I.Name,'delete' , 'event', GETDATE() ,  I.Creator_ID
		FROM DELETED I
end

END
go
----------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS friend_req_log_trig
GO

CREATE TRIGGER friend_req_log_trig
on friend_request
AFTER INSERT,UPDATE,DELETE
AS
BEGIN
SET NOCOUNT ON;
DECLARE @a int,@b int;
if exists(SELECT 1 from inserted) and exists (SELECT * from deleted)
begin
	
  --(@a,'UPDATE','friend_request', GETDATE() ,  inserted.User_Who_Sent_request_ID from inserted )
    INSERT into logs  
		SELECT 
         I.User_Who_Received_request_ID,'','friend_request','update', GETDATE() ,  I.User_Who_Sent_request_ID
		FROM inserted I
end

If exists (Select 1 from inserted) and not exists(Select * from deleted)
begin
    INSERT into logs  
		SELECT 
        I.User_Who_Received_request_ID,'','friend_request','INSERT', GETDATE() ,  I.User_Who_Sent_request_ID
		FROM inserted I
end

If exists(select 1 from deleted) and not exists(Select * from inserted)
begin 
   INSERT into logs  
		SELECT 
        I.User_Who_Received_request_ID,'','friend_requesd','deleted', GETDATE() ,  I.User_Who_Sent_request_ID
		FROM DELETED I
end

END

------------------------------------------------------------------------------------------------------------------------------
GO
DROP TRIGGER IF EXISTS friendship_log_trig
GO

CREATE TRIGGER friendship_log_trig
on friendship
AFTER INSERT,UPDATE,DELETE
AS
BEGIN
SET NOCOUNT ON;
DECLARE @a int,@b int;
if exists(SELECT 1 from inserted) and exists (SELECT * from deleted)
begin
	
  --(@a,'UPDATE','friend_request', GETDATE() ,  inserted.User_Who_Sent_request_ID from inserted )
    INSERT into logs  
		SELECT 
         I.UserR_ID,'','accepted_friend','update', GETDATE() ,  I.UserS_ID
		FROM inserted I
end

If exists (Select 1 from inserted) and not exists(Select * from deleted)
begin
   
          INSERT into logs  
		SELECT 
       I.UserR_ID,'','accepted_friend','insert', GETDATE() ,  I.UserS_ID
		FROM inserted I
end

If exists(select 1 from deleted) and not exists(Select * from inserted)
begin 
  
          INSERT into logs  
		SELECT 
        I.UserR_ID,'','accepted_friend','delete', GETDATE() ,  I.UserS_ID
		FROM DELETED I
end

END

------------------------------------------------------------------------------------------------------------------------------
go


DROP TRIGGER IF EXISTS Picture_log_trig
GO

CREATE TRIGGER Picture_log_trig
on Picture
AFTER INSERT,UPDATE,DELETE
AS
BEGIN
SET NOCOUNT ON;
if exists(SELECT 1 from inserted) and exists (SELECT * from deleted)
begin
	
  --(@a,'UPDATE','friend_request', GETDATE() ,  inserted.User_Who_Sent_request_ID from inserted )
    INSERT into logs  
		SELECT 
         I.Picture_ID,I.SRC,'update' , 'pic', GETDATE() ,  I.USERID
		FROM inserted I
end

If exists (Select 1 from inserted) and not exists(Select * from deleted)
begin
   
          INSERT into logs  
		SELECT 
        I.Picture_ID,I.SRC,'insert' , 'pic', GETDATE() ,  I.USERID
		FROM inserted I
end

If exists(select 1 from deleted) and not exists(Select * from inserted)
begin 
  
          INSERT into logs  
		SELECT 
          I.Picture_ID,I.SRC,'delete' , 'pic', GETDATE() ,  I.USERID
		FROM DELETED I
end

END
go

DROP TRIGGER IF EXISTS vid_log_trig
GO

CREATE TRIGGER vid_log_trig
on Video
AFTER INSERT,UPDATE,DELETE
AS
BEGIN
SET NOCOUNT ON;
if exists(SELECT 1 from inserted) and exists (SELECT * from deleted)
begin
	
  --(@a,'UPDATE','friend_request', GETDATE() ,  inserted.User_Who_Sent_request_ID from inserted )
    INSERT into logs  
		SELECT 
         I.Video_ID,I.Message,'update' , 'vid', GETDATE() ,  I.USERID
		FROM inserted I
end

If exists (Select 1 from inserted) and not exists(Select * from deleted)
begin
   
          INSERT into logs  
		SELECT 
         I.Video_ID,I.Message,'insert' , 'vid', GETDATE() ,  I.USERID
		FROM inserted I
end

If exists(select 1 from deleted) and not exists(Select * from inserted)
begin 
  
          INSERT into logs  
		SELECT 
          I.Video_ID,I.Message,'delete' , 'vid', GETDATE() ,  I.USERID
		FROM DELETED I
end

END
go

DROP TRIGGER IF EXISTS pic_album_log_trig
GO

CREATE TRIGGER pic_album_log_trig
on Picture_Album
AFTER INSERT,UPDATE,DELETE
AS
BEGIN
SET NOCOUNT ON;
if exists(SELECT 1 from inserted) and exists (SELECT * from deleted)
begin
	
  --(@a,'UPDATE','friend_request', GETDATE() ,  inserted.User_Who_Sent_request_ID from inserted )
    INSERT into logs  
		SELECT 
         I.Picture_Album_ID,I.Name,'update' , 'album', GETDATE() ,  I.USERID
		FROM inserted I
end

If exists (Select 1 from inserted) and not exists(Select * from deleted)
begin
   
          INSERT into logs  
		SELECT 
         I.Picture_Album_ID,I.Name,'insert' , 'album', GETDATE() ,  I.USERID
		FROM inserted I
end

If exists(select 1 from deleted) and not exists(Select * from inserted)
begin 
  
          INSERT into logs  
		SELECT 
          I.Picture_Album_ID,I.Name,'delete' , 'album', GETDATE() ,  I.USERID
		FROM DELETED I
end

END
go





SELECT * FROM LOGS