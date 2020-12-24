CREATE TABLE Privacy
(
  Privacy_Name VARCHAR(20) NOT NULL,
  PRIMARY KEY (Privacy_Name)
);
go

insert into Privacy values ('OPEN')
GO

CREATE TABLE Interest
(
  Interest_ID int identity(1,1) not null,
  Description VARCHAR(100) NOT NULL,
  PRIMARY KEY (Interest_ID),

);

CREATE TABLE LOC
(
  LOC_ID int identity(1,1) not null,
  Name VARCHAR(100) NOT NULL, --not unique because the test set has a location 2 times with diff id
  PRIMARY KEY (LOC_ID),

);

CREATE TABLE USERS
(
  UserID int identity(1,1) not null,
  Username VARCHAR(50) unique NOT NULL,
  Pass VARCHAR(50) NOT NULL,
  First_Name VARCHAR(50) NOT NULL,
  Last_Name VARCHAR(50) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  Website VARCHAR(1000) NOT NULL,
  Link VARCHAR(1000) NOT NULL,
  Birthday DATE NOT NULL,
  Gender bit NOT NULL,
  
  Is_verified bit NOT NULL,
  Hometown_LOC_ID INT  ,
  Current_LOC_ID INT  ,
 
  PRIMARY KEY (UserID),
  FOREIGN KEY (Hometown_LOC_ID) REFERENCES LOC(LOC_ID)
  --on delete SET NULL --Does not allow us this because of multiple paths
  --ON UPDATE CASCADE
  ,
  FOREIGN KEY (Current_LOC_ID) REFERENCES LOC(LOC_ID)
  on delete SET NULL
  ON UPDATE CASCADE
);

CREATE TABLE Link
(
  Link_ID int identity(1,1) not null,
  Name VARCHAR(50) NOT NULL,
  URL VARCHAR(1000) NOT NULL,
  Message VARCHAR(200),
  Description VARCHAR(500),
  Caption VARCHAR(200),
  UserID INT NOT NULL,
  PRIMARY KEY (Link_ID),
  FOREIGN KEY (UserID) REFERENCES USERS(UserID)
   ON DELETE CASCADE
   ON UPDATE CASCADE,
);



CREATE TABLE Video
(
  Video_ID INT identity(1,1) not null,
  Message VARCHAR(100) NOT NULL,
  Description VARCHAR(500),
  Length INT NOT NULL,
  SRC VARCHAR(1000),
  UserID INT NOT NULL,
  Privacy_Name VARCHAR(20) default 'OPEN',
  PRIMARY KEY (Video_ID),
  FOREIGN KEY (UserID) REFERENCES USERS(UserID)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (Privacy_Name) REFERENCES Privacy(Privacy_Name)
  ON DELETE SET default
  ON UPDATE CASCADE,
);

CREATE TABLE User_Quotes
(
  Quote VARCHAR(400) NOT NULL,
  UserID INT NOT NULL,
  PRIMARY KEY (Quote, UserID),
  FOREIGN KEY (UserID) REFERENCES USERS(UserID)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

CREATE TABLE User_Work
(
  Workplace VARCHAR(100) NOT NULL,
  UserID INT NOT NULL,
  PRIMARY KEY (Workplace, UserID),
  FOREIGN KEY (UserID) REFERENCES USERS(UserID) 
  ON DELETE CASCADE
  ON UPDATE CASCADE

);

CREATE TABLE User_Education
(
  Education_Place VARCHAR(100) NOT NULL,
  UserID INT NOT NULL,
  PRIMARY KEY (Education_Place, UserID),
  FOREIGN KEY (UserID) REFERENCES USERS(UserID)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

CREATE TABLE Video_Comments
(
  Comment VARCHAR(100) NOT NULL,
  Comment_ID INT identity(1,1) NOT NULL,
  Video_ID INT NOT NULL,
  UserID INT NOT NULL,
  PRIMARY KEY (Comment_ID, Video_ID),
  FOREIGN KEY (Video_ID) REFERENCES Video(Video_ID)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (UserID) REFERENCES USERS(UserID)
  --ON DELETE CASCADE --did not allow us this
  --ON UPDATE CASCADE
);

CREATE TABLE User_Interest
(
  Interest_ID INT NOT NULL,
  UserID INT NOT NULL,
  PRIMARY KEY (Interest_ID, UserID),
  FOREIGN KEY (Interest_ID) REFERENCES Interest(Interest_ID)
   ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (UserID) REFERENCES USERS(UserID)
   ON DELETE CASCADE
  ON UPDATE CASCADE
);

CREATE TABLE Friend_Request
(
  User_Who_Sent_request_ID INT NOT NULL,
  User_Who_Received_request_ID INT NOT NULL CHECK (User_Who_Sent_request_ID != User_Who_Received_request_ID),
  ignored bit NOT NULL DEFAULT 1,
  PRIMARY KEY (User_Who_Sent_request_ID, User_Who_Received_request_ID),
  FOREIGN KEY (User_Who_Sent_request_ID) REFERENCES USERS(UserID)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (User_Who_Received_request_ID) REFERENCES USERS(UserID)
  --ON DELETE CASCADE  --did not allow us this
  --ON UPDATE CASCADE,
  
);

CREATE TABLE FriendShip
(
  UserS_ID INT NOT NULL,
  UserR_ID INT NOT NULL CHECK (UserS_ID != UserR_ID),
  Primary key (UserS_ID,UserR_ID),
  FOREIGN KEY (UserS_ID) REFERENCES USERS(UserID)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (UserR_ID) REFERENCES USERS(UserID)

  -- ON DELETE CASCADE --did not allow us this
  --ON UPDATE CASCADE
);


CREATE TABLE GATHERING
(
  GATHERING_ID INT identity(1,1) NOT NULL,
  Venue VARCHAR(100) NOT NULL,
  Name VARCHAR(100) NOT NULL,
  Start_time DATETIME NOT NULL,
  End_time DATETIME NOT NULL,
  Description_ VARCHAR(500),
  Creator_ID INT NOT NULL,
  LOC_ID INT NOT NULL,
  Privacy_Name VARCHAR(20) DEFAULT 'OPEN' ,
  PRIMARY KEY (GATHERING_ID),
  FOREIGN KEY (Creator_ID) REFERENCES USERS(UserID)
   ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (LOC_ID) REFERENCES LOC(LOC_ID),
  -- ON DELETE CASCADE --did not allow us this
  --ON UPDATE CASCADE,
  FOREIGN KEY (Privacy_Name) REFERENCES Privacy(Privacy_Name)
  -- ON DELETE SET DEFAULT --did not allow us this
  --ON UPDATE CASCADE
);

CREATE TABLE Picture_Album
(
  Picture_Album_ID INT identity(1,1) NOT NULL,
  Name VARCHAR(100) NOT NULL,
  Description VARCHAR(500),
  Link VARCHAR(1000),
  Taken_LOC_ID INT,
  UserID INT NOT NULL,
  Privacy_Name VARCHAR(20) DEFAULT 'OPEN',
  PRIMARY KEY (Picture_Album_ID),
  FOREIGN KEY (Taken_LOC_ID) REFERENCES LOC(LOC_ID)
  ON DELETE set NULL
  ON UPDATE CASCADE,
  FOREIGN KEY (UserID) REFERENCES USERS(UserID) ,
  --ON DELETE CASCADE --did not allow us this
  --ON UPDATE CASCADE,
  FOREIGN KEY (Privacy_Name) REFERENCES Privacy(Privacy_Name)
  -- ON DELETE SET DEFAULT --did not allow us this
  --ON UPDATE CASCADE
);

CREATE TABLE Picture
(
  Picture_ID INT IDENTITY(1,1) NOT NULL,
   Width FLOAT NOT NULL,
   Height FLOAT NOT NULL,
  Link VARCHAR(1000),
    SRC VARCHAR(1000),
  Privacy_Name VARCHAR(20) DEFAULT 'OPEN',
  USERID INT NOT NULL,
  PRIMARY KEY (Picture_ID),
  FOREIGN KEY (USERID) REFERENCES USERS(USERID)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (Privacy_Name) REFERENCES Privacy(Privacy_Name)
  --ON DELETE SET DEFAULT --did not allow us this
  --ON UPDATE CASCADE

);

CREATE TABLE Picture_Album_Comments
(
  Comment VARCHAR(100) NOT NULL,
  Comment_ID INT identity(1,1) NOT NULL,
  Picture_Album_ID INT NOT NULL,
  UserID INT NOT NULL,
  PRIMARY KEY (Comment_ID, Picture_Album_ID),
  FOREIGN KEY (Picture_Album_ID) REFERENCES Picture_Album(Picture_Album_ID)
  ON DELETE CASCADE
  ON UPDATE CASCADE
  ,
  FOREIGN KEY (UserID) REFERENCES USERS(UserID) 
  --ON DELETE CASCADE --did not allow us this
  --ON UPDATE CASCADE

);

CREATE TABLE GATHERING_Attendances
(
  GATHERING_ID INT NOT NULL,
  UserID INT NOT NULL,
  PRIMARY KEY (GATHERING_ID, UserID),
  FOREIGN KEY (GATHERING_ID) REFERENCES GATHERING(GATHERING_ID) 
  ON DELETE CASCADE
  ON UPDATE CASCADE
  ,
  FOREIGN KEY (UserID) REFERENCES USERS(UserID) 
  --ON DELETE CASCADE --did not allow us this
  --ON UPDATE CASCADE
);

CREATE TABLE Picture_in_Album
(
  Picture_Album_ID INT NOT NULL,
  Picture_ID INT,
  PRIMARY KEY (Picture_Album_ID, Picture_ID),
  FOREIGN KEY (Picture_Album_ID) REFERENCES Picture_Album(Picture_Album_ID) 
  ON DELETE CASCADE
  ON UPDATE CASCADE
  ,
  FOREIGN KEY (Picture_ID) REFERENCES Picture(Picture_ID)
  -- ON DELETE CASCADE --did not allow us this
  --ON UPDATE CASCADE
);

CREATE TABLE User_Likes_Picture
(
  UserID INT NOT NULL,
  Picture_ID INT NOT NULL,
  PRIMARY KEY (UserID, Picture_ID),
  FOREIGN KEY (UserID) REFERENCES USERS(UserID) 
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (Picture_ID) REFERENCES Picture(Picture_ID)
  --ON DELETE CASCADE --did not allow us this
  --ON UPDATE CASCADE
);
go
CREATE TABLE Picture_comments
(
  pic_com_ID INT identity(1,1) NOT NULL,
  COMMENT VARCHAR(100) NOT NULL,
  UserID INT NOT NULL,
  Picture_ID INT NOT NULL,
  PRIMARY KEY (pic_com_ID),
  FOREIGN KEY (UserID) REFERENCES USERS(UserID)
   ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (Picture_ID) REFERENCES Picture(Picture_ID)
  --ON DELETE CASCADE --did not allow us this
  --ON UPDATE CASCADE

);

GO
CREATE TABLE Logs
(
  ObjectID INT NOT NULL,
  Obj_name NVARCHAR(25) NOT NULL,
  action NVARCHAR(25) NOT NULL,
  Object_Type NVARCHAR(25) NOT NULL,
  Date_Time DATETIME NOT NULL,
  UserID INT NOT NULL,
  PRIMARY KEY (ObjectID, Object_Type, Date_Time, UserID),
  FOREIGN KEY (UserID) REFERENCES USERS(UserID)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);


--CREATE TYPE StringTable 
--   AS TABLE
--      ( STRING VARCHAR(50));
--GO