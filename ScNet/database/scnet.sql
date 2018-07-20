CREATE SCHEMA IF NOT EXISTS `scnet` DEFAULT CHARACTER SET utf8;

use scnet;

CREATE TABLE IF NOT EXISTS `scnet`.`User` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `joinDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` VARCHAR(250) NOT NULL,
  `status` INT SIGNED NOT NULL DEFAULT 1 COMMENT '0 - Registered\n1 - Verified Email/ Active\n-1 - Disable by User\n-2 - Disable by System',
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `user_UNIQUE` (`email` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `scnet`.`Post` (
  `postId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `text` VARCHAR(8000) NULL COMMENT 'This field is for status and image description',
  `postDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`postId`),
  INDEX `user_post_fk_idx` (`userId` ASC),
  CONSTRAINT `user_post_fk`
    FOREIGN KEY (`userId`)
    REFERENCES `scnet`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `scnet`.`Image` (
  `postId` INT NOT NULL AUTO_INCREMENT,
  `imageName` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`postId`),
  CONSTRAINT `post_image_fk`
    FOREIGN KEY (`postId`)
    REFERENCES `scnet`.`Post` (`postId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `scnet`.`Comment` (
  `commentId` INT NOT NULL AUTO_INCREMENT,
  `postId` INT NOT NULL,
  `comment` VARCHAR(8000) NOT NULL,
  `commentDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `commentBy` INT NOT NULL,
  PRIMARY KEY (`commentId`),
  INDEX `post_comment_fk_idx` (`postId` ASC),
  INDEX `user_comment_fk_idx` (`commentBy` ASC),
  CONSTRAINT `post_comment_fk`
    FOREIGN KEY (`postId`)
    REFERENCES `scnet`.`Post` (`postId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_comment_fk`
    FOREIGN KEY (`commentBy`)
    REFERENCES `scnet`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


DELIMITER $$
USE `scnet`$$
CREATE PROCEDURE `insertUser` (IN pName varchar(250), IN pEmail varchar(45), IN pPassword varchar(45), OUT pId int)
BEGIN
	INSERT INTO USER(NAME,EMAIL,PASSWORD) VALUES(pName, pEmail, pPassword);
    
    SELECT LAST_INSERT_ID() INTO pId;
END$$

DELIMITER ;



DELIMITER $$
USE `scnet`$$
CREATE PROCEDURE `insertComment` (IN pPostId INT, IN pComment VARCHAR(8000), IN pUserId INT, OUT pId INT)
BEGIN
	INSERT INTO comment(postId, comment, commentBy) VALUES(pPostId, pComment, pUserId);
    
    SELECT LAST_INSERT_ID() INTO pId;
END$$

DELIMITER ;



DELIMITER $$
USE `scnet`$$
CREATE PROCEDURE `insertImage` (IN pUserId INT, IN pText VARCHAR(8000), IN pImageName varchar(250), OUT pId INT)
BEGIN
	START TRANSACTION;
	CALL insertPost(pUserId, pText, @a);
    
    SELECT @a INTO pId FROM dual;
    
    INSERT INTO Image(postId, imageName) values(pId, pImageName);
    COMMIT;
END$$

DELIMITER ;



DELIMITER $$
USE `scnet`$$
CREATE PROCEDURE `insertPost` (IN pUserId INT, IN pText VARCHAR(8000), OUT pId INT)
BEGIN
	INSERT INTO POST(userId, text) values(pUserId, pText);
    
    SELECT LAST_INSERT_ID() INTO pId;
END$$

DELIMITER ;



DELIMITER $$
USE `scnet`$$
CREATE PROCEDURE `updateUser` (IN pId INT, IN pStatus INT)
BEGIN
	UPDATE USER SET STATUS = pStatus where userId = pId;
END$$

DELIMITER ;



DELIMITER $$


USE `scnet`$$


CREATE PROCEDURE `isUserExists`(IN pEmail varchar(45), out isExists boolean)


BEGIN


	SELECT true into isExists from User where email = pEmail;


END$$





DELIMITER ;



DELIMITER $$

USE `scnet`$$

CREATE PROCEDURE getUserbyEmail(IN pEmail varchar(45))
BEGIN

    SELECT userId, email, joinDate, name, status

    FROM user

    where email = pEmail;
END$$



DELIMITER ;


DELIMITER $$

CREATE PROCEDURE getPost (IN pUserId INT, IN pFromRec INT, IN pNoOfRec INT)

BEGIN
    SELECT IF(pNoOfRec=0, count(*), pNoOfRec), IF(pNoOfRec=0, 0, pFromRec)

    into pFromRec, pNoOfRec

    from post where userId = pUserId;

    SELECT post.postId, userId, text, postDate, imageName
    FROM post


    LEFT JOIN image on post.postId = image.postId
    WHERE userId = pUserId

    LIMIT pFromRec, pNoOfRec;


END$$



DELIMITER ;


DELIMITER $$

USE `scnet`$$

CREATE PROCEDURE `getComment`(IN pPostId INT, IN pfromRec INT, IN pNoOfRec INT)

BEGIN

    SELECT IF(pNoOfRec=0, count(*), pNoOfRec), IF(pNoOfRec=0, 0, pFromRec)

    into pFromRec, pNoOfRec

    from post where userId = pUserId;

    SELECT commentId, postId, comment, commentDate, commentBy
, user.name
    FROM comment

    inner join user on comment.commentBy = user.userId
    WHERE postId = pPostId

    LIMIT pfromRec, pNoOfRec;

END$$


DELIMITER ;
