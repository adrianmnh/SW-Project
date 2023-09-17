
USE [Master];
GO

ALTER DATABASE [SummonersWar] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
go

DROP DATABASE [SummonersWar];
go

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

CREATE TYPE [GameTool].[PrimaryKey6]
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

CREATE TYPE [GameTool].[ForeignKey]
    FROM INT NOT NULL
    go

    sp_addsrvrolemember 'admin', 'sysadmin'
    go

CREATE TABLE [GameTool].[Account]
(
    [AccountId]          [GameTool].[PrimaryKey]  IDENTITY ( 10,1 ) ,
    [AccountUsername]    [GameTool].[Username] ,
    [AccountPassword]    [GameTool].[Password] ,
    [AccountEmail]       [GameTool].[Email] ,
    CONSTRAINT [XPKAccount] PRIMARY KEY  CLUSTERED ([AccountId] ASC)
    )
    go

CREATE TABLE [GameTool].[Monster]
(
    [MonsterId]          [GameTool].[PrimaryKey3]  IDENTITY ( 1000,1 ) ,
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
    CONSTRAINT [XPKMonster] PRIMARY KEY  CLUSTERED ([MonsterId] ASC)
    )
    go

CREATE TABLE [GameTool].[Summon]
(
    [AccountMonsterId]   [GameTool].[PrimaryKey3]  IDENTITY ( 1000,1 ) ,
    [MonsterId]          [GameTool].[PrimaryKey3]  NULL
    INDEX [XIF1AccontMonster] NONCLUSTERED  ,
    CONSTRAINT [XPKAccontMonster] PRIMARY KEY  CLUSTERED ([AccountMonsterId] ASC),
    CONSTRAINT [FK_MonsterSummon] FOREIGN KEY ([MonsterId]) REFERENCES [GameTool].[Monster]([MonsterId])
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    )
    go

CREATE TABLE [GameTool].[Rune]
(
    [RuneId]             [GameTool].[PrimaryKey6]  IDENTITY ( 1000000,1 ) ,
    [AccountId]          [GameTool].[PrimaryKey]  NOT NULL
    INDEX [XIF1Rune] NONCLUSTERED  ,
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
    CONSTRAINT [XPKRune] PRIMARY KEY  CLUSTERED ([RuneId] ASC),
    CONSTRAINT [FK_AccountRune] FOREIGN KEY ([AccountId]) REFERENCES [GameTool].[Account]([AccountId])
    ON DELETE SET NULL
    ON UPDATE NO ACTION
    )
    go

CREATE TABLE [GameTool].[Engraved]
(
    [AccountId]          [GameTool].[PrimaryKey]
     INDEX [XIF1AccountAccontMonster] NONCLUSTERED  ,
    [MonsterId]          [GameTool].[PrimaryKey3]
     INDEX [XIF2AccountAccontMonster] NONCLUSTERED  ,
    [Rune1]              [GameTool].[PrimaryKey6]  NULL
     INDEX [XIF3AccountAccontMonster] NONCLUSTERED  ,
    [Rune2]              [GameTool].[PrimaryKey6]  NULL
     INDEX [XIF4AccountAccontMonster] NONCLUSTERED  ,
    [Rune3]              [GameTool].[PrimaryKey6]  NULL
     INDEX [XIF5AccountAccontMonster] NONCLUSTERED  ,
    [Rune4]              [GameTool].[PrimaryKey6]  NULL
     INDEX [XIF6AccountAccontMonster] NONCLUSTERED  ,
    [Rune5]              [GameTool].[PrimaryKey6]  NULL
     INDEX [XIF7AccountAccontMonster] NONCLUSTERED  ,
    [Rune6]              [GameTool].[PrimaryKey6]  NULL
     INDEX [XIF8AccountAccontMonster] NONCLUSTERED  ,
     CONSTRAINT [XPKAccountAccontMonster] PRIMARY KEY  CLUSTERED ([AccountId] ASC,[MonsterId] ASC),
    CONSTRAINT [PK_AccountEngraved] FOREIGN KEY ([AccountId]) REFERENCES [GameTool].[Account]([AccountId])
    ON UPDATE NO ACTION,
    CONSTRAINT [PK_SummonEngraved] FOREIGN KEY ([MonsterId]) REFERENCES [GameTool].[Summon]([AccountMonsterId])
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune1] FOREIGN KEY ([Rune1]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune2] FOREIGN KEY ([Rune2]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune3] FOREIGN KEY ([Rune3]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune4] FOREIGN KEY ([Rune4]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune5] FOREIGN KEY ([Rune5]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
    CONSTRAINT [FK_Rune6] FOREIGN KEY ([Rune6]) REFERENCES [GameTool].[Rune]([RuneId])
    ON DELETE SET NULL
    ON UPDATE NO ACTION
    )
    go

    SET IDENTITY_INSERT GameTool.Account ON

    GO

    INSERT INTO GameTool.Account
(AccountId, AccountUserName, AccountPassword, AccountEmail)
    VALUES
(1, 'adrian', 'password', 'anoa_apps@icloud.com'),
(10, 'adrian2', 'password', 'email');

GO

SET IDENTITY_INSERT GameTool.Account OFF

GO

INSERT INTO GameTool.Monster
VALUES
    ('Artamiel', '11535', '604', '769', '95', '15', '50', '40', '0'),
    ('Barbara', '9720', '845', '648', '108', '15', '50', '15', '25'),
    ('Bastet', '11850', '637', '714', '99', '15', '50', '40', '0'),
    ('Bellenus', '10215', '703', '758', '99', '15', '50', '15', '0'),
    ('Chiwu', '12180', '780', '549', '103', '15', '50', '15', '40'),
    ('Ethna', '10380', '845', '604', '119', '30', '50', '15', '0'),
    ('Laika', '11040', '834', '571', '100', '15', '50', '15', '0'),
    ('Monkey', '12180', '692', '637', '118', '15', '50', '15', '0'),
    ('Oberon', '10050', '790', '681', '92', '15', '50', '40', '0'),
    ('Perna', '12345', '878', '439', '109', '15', '50', '15', '0'),
    ('Ryu', '10875', '823', '593', '103', '15', '50', '40', '15'),
    ('Seara', '10875', '801', '615', '100', '15', '50', '15', '25'),
    ('Susano', '8070', '911', '527', '107', '15', '50', '15', '25'),
    ('Sylvia', '11040', '692', '714', '104', '15', '50', '15', '0'),
    ('Theomars', '10875', '823', '593', '100', '30', '50', '15', '0'),
    ('Tiana', '11850', '725', '626', '96', '15', '50', '40', '0');

GO


INSERT INTO GameTool.Rune
(AccountId, RuneGrade, RuneSet, RunePosition, RuneInnate, RuneMainStat, Runestat1, Runestat1val, Runestat2, Runestat2val, Runestat3, Runestat3val, Runestat4, Runestat4val, Runeinnatestat, Runeinnateval)
VALUES
(1, 6, 'Violent', 1, 1, 'ATK', 'HP%', 23, 'ACC', 16, 'SPD', 5, 'CRte', 10, 'CDmg', 6),
(1, 6, 'Violent', 6, 0, 'ATK%', 'CDmg', 11, 'SPD', 25, 'CRte', 5, 'HP%', 22, NULL, NULL),
(1, 6, 'Violent', 4, 1, 'ATK%', 'HP%', 17, 'DEF', 55, 'SPD', 25, 'DEF%', 13, 'ATK', 18),
(1, 6, 'Violent', 3, 1, 'DEF', 'ACC', 11, 'SPD', 14, 'CRte', 4, 'HP%', 16, 'DEF%', 8),
(1, 6, 'Nemesis', 2, 0, 'SPD', 'ATK%', 15, 'HP%', 16, 'CRte', 16, 'RES', 13, NULL, NULL),
(1, 6, 'Nemesis', 5, 1, 'HP', 'RES', 12, 'SPD', 24, 'ATK%', 11, 'HP%', 14, 'DEF%', 7);


GO
