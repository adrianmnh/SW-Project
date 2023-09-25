
USE [Master];
GO

-- ALTER DATABASE [SummonersWar] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
-- go
--
-- DROP DATABASE [SummonersWar];
-- go

CREATE DATABASE [SummonersWar];
go

ALTER DATABASE [SummonersWar]
SET
MULTI_USER ,
READ_WRITE
go


USE [SummonersWar]

GO

DROP LOGIN [admin]
go

CREATE LOGIN admin
WITH PASSWORD = 'admin$%#@29174$%#@',
DEFAULT_DATABASE = SummonersWar
go

CREATE SCHEMA [GameTool]
    go

CREATE USER [useraccount]
FOR LOGIN [admin]
WITH DEFAULT_SCHEMA = GameTool
go

CREATE TYPE [GameTool].[SWToolDomains]
    FROM CHAR(5) NOT NULL
    go

CREATE TYPE [GameTool].[udtString]
    FROM VARCHAR(20) NOT NULL
    go

CREATE TYPE [GameTool].[udtNumeric]
    FROM INT NOT NULL
    go

CREATE TYPE [GameTool].[Set]
    FROM VARCHAR(15) NOT NULL
    go

CREATE TYPE [GameTool].[Stat]
    FROM VARCHAR(5) NOT NULL
    go

CREATE TYPE [GameTool].[Grade]
    FROM TINYINT NOT NULL
    go

CREATE TYPE [GameTool].[dRuneAttributes]
    FROM CHAR(5) NOT NULL
    go

CREATE TYPE [GameTool].[Innate]
    FROM TINYINT NOT NULL
    go

CREATE TYPE [GameTool].[Position]
    FROM TINYINT NOT NULL
    go

CREATE TYPE [GameTool].[StatValue]
    FROM SMALLINT NOT NULL
    go

CREATE TYPE [GameTool].[PrimaryKey]
    FROM INT NOT NULL
    go

CREATE TYPE [GameTool].[InnateStat]
    FROM VARCHAR(5) NULL
    go

CREATE TYPE [GameTool].[InnateStatValue]
    FROM SMALLINT NULL
    go

CREATE TYPE [GameTool].[dAccount]
    FROM CHAR(5) NOT NULL
    go

CREATE TYPE [GameTool].[Username]
    FROM VARCHAR(20) NOT NULL
    go

CREATE TYPE [GameTool].[Password]
    FROM VARCHAR(20) NOT NULL
    go

CREATE TYPE [GameTool].[Email]
    FROM VARCHAR(50) NOT NULL
    go

CREATE TYPE [GameTool].[PrimaryKey4]
    FROM INT NOT NULL
    go

CREATE TYPE [GameTool].[PrimaryKey3]
    FROM INT NOT NULL
    go

CREATE TYPE [GameTool].[dMonster]
    FROM CHAR(5) NOT NULL
    go

CREATE TYPE [GameTool].[MonsterName]
    FROM NVARCHAR(20) NOT NULL
    go

CREATE TYPE [GameTool].[MonsterStatValue]
    FROM INTEGER NOT NULL
    go

CREATE TYPE [GameTool].[SurrogateKey]
    FROM INT NOT NULL
    go

CREATE TYPE [PrimaryKey2]
    FROM INT NOT NULL
    go

CREATE TYPE [BooleanInt]
    FROM TINYINT NOT NULL
    go

    sp_addsrvrolemember 'admin', 'sysadmin'
    go

CREATE TABLE [GameTool].[Account]
(
    [AccountId]          [GameTool].[PrimaryKey]  IDENTITY ( 10,1 ) ,
    [AccountUsername]    [GameTool].[Username] ,
    [AccountPassword]    [GameTool].[Password] ,
    [AccountEmail]       [GameTool].[Email] ,
    CONSTRAINT [PK_Account] PRIMARY KEY  CLUSTERED ([AccountId] ASC),
    CONSTRAINT [AK_UniqueEmail] UNIQUE ([AccountEmail]  ASC),
    CONSTRAINT [AK_UniqueUsername] UNIQUE ([AccountUsername]  ASC)
    )
    go

CREATE TABLE [GameTool].[Monster]
(
    [MonsterId]          [PrimaryKey2]  IDENTITY ( 100,1 ) ,
    [MonsterName]        [GameTool].[MonsterName] ,
    [MonsterHP]          [GameTool].[MonsterStatValue]
    CONSTRAINT [CK_StatValue_1345395308]
    CHECK  ( MonsterHP BETWEEN 0 AND 999999 ),
    [MonsterATK]         [GameTool].[MonsterStatValue]
    CONSTRAINT [CK_StatValue_827403536]
    CHECK  ( MonsterATK BETWEEN 0 AND 999999 ),
    [MonsterDEF]         [GameTool].[MonsterStatValue]
    CONSTRAINT [CK_StatValue_827210773]
    CHECK  ( MonsterDEF BETWEEN 0 AND 999999 ),
    [MonsterSPD]         [GameTool].[MonsterStatValue]
    CONSTRAINT [CK_StatValue_826224919]
    CHECK  ( MonsterSPD BETWEEN 0 AND 999999 ),
    [MonsterCRte]        [GameTool].[MonsterStatValue]
    CONSTRAINT [CK_StatValue_1331604439]
    CHECK  ( MonsterCRte BETWEEN 0 AND 999999 ),
    [MonsterCDmg]        [GameTool].[MonsterStatValue]
    CONSTRAINT [CK_StatValue_1332523733]
    CHECK  ( MonsterCDmg BETWEEN 0 AND 999999 ),
    [MonsterRES]         [GameTool].[MonsterStatValue]
    CONSTRAINT [CK_StatValue_826293256]
    CHECK  ( MonsterRES BETWEEN 0 AND 999999 ),
    [MonsterACC]         [GameTool].[MonsterStatValue]
    CONSTRAINT [CK_StatValue_827407896]
    CHECK  ( MonsterACC BETWEEN 0 AND 999999 ),
    CONSTRAINT [PK_Monster] PRIMARY KEY  CLUSTERED ([MonsterId] ASC),
    CONSTRAINT [AK_UniqueMonster] UNIQUE ([MonsterName]  ASC)
    )
    go

CREATE TABLE [GameTool].[Summon]
(
    [SummonId]           [GameTool].[PrimaryKey3]  IDENTITY ( 1000,1 ) ,
    [AccountId]          [GameTool].[PrimaryKey]  NULL
    INDEX [XFK_AccountSummon] NONCLUSTERED  ,
    [MonsterId]          [PrimaryKey2]  NOT NULL
    INDEX [XFK_MonsterSummon] NONCLUSTERED  ,
    [SummonName]         [GameTool].[MonsterName] ,
    CONSTRAINT [PK_Summon] PRIMARY KEY  CLUSTERED ([SummonId] ASC),
    CONSTRAINT [AK_UniqueSummon] UNIQUE ([MonsterId]  ASC,[SummonName]  ASC,[AccountId]  ASC),
    CONSTRAINT [FK_Account_Summon] FOREIGN KEY ([AccountId]) REFERENCES [GameTool].[Account]([AccountId])
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Monster_Summon] FOREIGN KEY ([MonsterId]) REFERENCES [GameTool].[Monster]([MonsterId])
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    )
    go

CREATE TABLE [GameTool].[Rune]
(
    [RuneId]             [PrimaryKey2]  IDENTITY ( 100,1 ) ,
    [AccountId]          [GameTool].[PrimaryKey]  NOT NULL
    INDEX [XFK_AccountRune] NONCLUSTERED  ,
    [RuneGrade]          [GameTool].[Grade]
    CONSTRAINT [CK_Grade_317387518]
    CHECK  ( [RuneGrade]=5 OR [RuneGrade]=6 ),
    [RuneSet]            [GameTool].[Set]
    CONSTRAINT [CK_Set_1348176969]
    CHECK  ( [RuneSet]='Violent' OR [RuneSet]='Swift' OR [RuneSet]='Rage' OR [RuneSet]='Will' OR [RuneSet]='Nemesis' OR [RuneSet]='Blade' ),
    [RunePosition]       [GameTool].[Position]
    CONSTRAINT [CK_Position_1736831478]
    CHECK  ( RunePosition BETWEEN 1 AND 6 ),
    [RuneInnate]         [GameTool].[Innate]
    CONSTRAINT [CK_Innate_433882015]
    CHECK  ( [RuneInnate]=0 OR [RuneInnate]=1 ),
    [RuneMainStat]       [GameTool].[Stat]
    CONSTRAINT [CK_Stat_16608635]
    CHECK  ( [RuneMainStat]='HP%' OR [RuneMainStat]='ATK%' OR [RuneMainStat]='DEF%' OR [RuneMainStat]='SPD' OR [RuneMainStat]='CRte' OR [RuneMainStat]='CDmg' OR [RuneMainStat]='RES' OR [RuneMainStat]='ACC' OR [RuneMainStat]='HP' OR [RuneMainStat]='ATK' OR [RuneMainStat]='DEF' ),
    [Runestat1]          [GameTool].[Stat]
    CONSTRAINT [CK_Stat_1659442414]
    CHECK  ( [Runestat1]='HP%' OR [Runestat1]='ATK%' OR [Runestat1]='DEF%' OR [Runestat1]='SPD' OR [Runestat1]='CRte' OR [Runestat1]='CDmg' OR [Runestat1]='RES' OR [Runestat1]='ACC' OR [Runestat1]='HP' OR [Runestat1]='ATK' OR [Runestat1]='DEF' ),
    [Runestat1val]       [GameTool].[StatValue]
    CONSTRAINT [CK_PositiveInteger_857815537]
    CHECK  ( Runestat1val >= 1 ),
    [Runestat2]          [GameTool].[Stat]
    CONSTRAINT [CK_Stat_1642665198]
    CHECK  ( [Runestat2]='HP%' OR [Runestat2]='ATK%' OR [Runestat2]='DEF%' OR [Runestat2]='SPD' OR [Runestat2]='CRte' OR [Runestat2]='CDmg' OR [Runestat2]='RES' OR [Runestat2]='ACC' OR [Runestat2]='HP' OR [Runestat2]='ATK' OR [Runestat2]='DEF' ),
    [Runestat2val]       [GameTool].[StatValue]
    CONSTRAINT [CK_PositiveInteger_857815793]
    CHECK  ( Runestat2val >= 1 ),
    [Runestat3]          [GameTool].[Stat]
    CONSTRAINT [CK_Stat_1625887982]
    CHECK  ( [Runestat3]='HP%' OR [Runestat3]='ATK%' OR [Runestat3]='DEF%' OR [Runestat3]='SPD' OR [Runestat3]='CRte' OR [Runestat3]='CDmg' OR [Runestat3]='RES' OR [Runestat3]='ACC' OR [Runestat3]='HP' OR [Runestat3]='ATK' OR [Runestat3]='DEF' ),
    [Runestat3val]       [GameTool].[StatValue]
    CONSTRAINT [CK_PositiveInteger_857816049]
    CHECK  ( Runestat3val >= 1 ),
    [Runestat4]          [GameTool].[Stat] ,
    [Runestat4val]       [GameTool].[StatValue]
    CONSTRAINT [CK_PositiveInteger_857816305]
    CHECK  ( Runestat4val >= 1 ),
    [Runeinnatestat]     [GameTool].[InnateStat] ,
    [Runeinnateval]      [GameTool].[InnateStatValue] ,
    [Engraved]           [BooleanInt]
    CONSTRAINT [DF_BooleanFalse_1165685170]
    DEFAULT  0,
    CONSTRAINT [PK_Rune] PRIMARY KEY  CLUSTERED ([RuneId] ASC),
    CONSTRAINT [FK_Account_Rune] FOREIGN KEY ([AccountId]) REFERENCES [GameTool].[Account]([AccountId])
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    )
    go

CREATE TABLE [GameTool].[Engraved]
(
    [AccountId]          [GameTool].[PrimaryKey]
     INDEX [XPK_AccountEngraved] NONCLUSTERED  ,
    [SummonId]           [GameTool].[PrimaryKey3]
     INDEX [XPK_SummonEngraved] NONCLUSTERED  ,
    [Rune1]              [PrimaryKey2]  NULL
     INDEX [XFK_Rune1] NONCLUSTERED  ,
    [Rune2]              [PrimaryKey2]  NULL
     INDEX [XFK_Rune2] NONCLUSTERED  ,
    [Rune3]              [PrimaryKey2]  NULL
     INDEX [XFK_Rune3] NONCLUSTERED  ,
    [Rune4]              [PrimaryKey2]  NULL
     INDEX [XFK_Rune4] NONCLUSTERED  ,
    [Rune5]              [PrimaryKey2]  NULL
     INDEX [XFK_Rune5] NONCLUSTERED  ,
    [Rune6]              [PrimaryKey2]  NULL
     INDEX [XFK_Rune6] NONCLUSTERED  ,
     CONSTRAINT [PK_AccountMonster] PRIMARY KEY  CLUSTERED ([AccountId] ASC,[SummonId] ASC),
    CONSTRAINT [AK_UniqueRunes] UNIQUE ([Rune1]  ASC,[Rune2]  ASC,[Rune3]  ASC,[Rune4]  ASC,[Rune5]  ASC,[Rune6]  ASC,[SummonId]  ASC),
    CONSTRAINT [FK_Rune1] FOREIGN KEY ([Rune1]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune2] FOREIGN KEY ([Rune2]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune3] FOREIGN KEY ([Rune3]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune4] FOREIGN KEY ([Rune4]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune5] FOREIGN KEY ([Rune5]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune6] FOREIGN KEY ([Rune6]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT [PK_EngravedAccount] FOREIGN KEY ([AccountId]) REFERENCES [GameTool].[Account]([AccountId])
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT [PK_EngravedSummon] FOREIGN KEY ([SummonId]) REFERENCES [GameTool].[Summon]([SummonId])
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    go

CREATE OR ALTER TRIGGER GameTool.[Ti_AddEngraved] ON GameTool.Summon
    AFTER INSERT

  AS

BEGIN
  DECLARE  @numrows int

SELECT @numrows = @@rowcount

           PRINT(@numrows)

DECLARE @AccountId integer
DECLARE @MonsterId integer

    IF (EXISTS (SELECT * FROM inserted))
BEGIN
INSERT INTO GameTool.Engraved ( AccountId, SummonId )
SELECT AccountId, SummonId from Inserted


END





  RETURN

END

go