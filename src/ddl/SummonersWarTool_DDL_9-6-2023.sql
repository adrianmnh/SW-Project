
DROP TABLE [GameTool].[Monster]
    go

DROP TABLE [GameTool].[Rune]
    go

DROP TABLE [GameTool].[Account]
    go

DROP USER [useraccount]
go

DROP LOGIN [admin]
go

DROP TYPE [SWToolDomains]
    go

DROP TYPE [udtString]
    go

DROP TYPE [udtNumeric]
    go

DROP TYPE [Set]
    go

DROP TYPE [Stat]
    go

DROP TYPE [Grade]
    go

DROP TYPE [dRuneAttributes]
    go

DROP TYPE [Innate]
    go

DROP TYPE [Position]
    go

DROP TYPE [StatValue]
    go

DROP TYPE [PrimaryKey]
    go

DROP TYPE [InnateStat]
    go

DROP TYPE [InnateStatValue]
    go

DROP TYPE [dAccount]
    go

DROP TYPE [Username]
    go

DROP TYPE [Password]
    go

DROP TYPE [Email]
    go

DROP TYPE [PrimaryKey6]
    go

DROP TYPE [PrimaryKey3]
    go

DROP TYPE [dMonster]
    go

DROP TYPE [MonsterName]
    go

DROP TYPE [MonsterStatValue]
    go

DROP TYPE [ForeignKey]
    go

DROP SCHEMA [GameTool]
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

CREATE TYPE [SWToolDomains]
    FROM CHAR(5) NOT NULL
    go

CREATE TYPE [udtString]
    FROM VARCHAR(20) NOT NULL
    go

CREATE TYPE [udtNumeric]
    FROM INT NOT NULL
    go

CREATE TYPE [Set]
    FROM VARCHAR(15) NOT NULL
    go

CREATE TYPE [Stat]
    FROM VARCHAR(5) NOT NULL
    go

CREATE TYPE [Grade]
    FROM TINYINT NOT NULL
    go

CREATE TYPE [dRuneAttributes]
    FROM CHAR(5) NOT NULL
    go

CREATE TYPE [Innate]
    FROM TINYINT NOT NULL
    go

CREATE TYPE [Position]
    FROM TINYINT NOT NULL
    go

CREATE TYPE [StatValue]
    FROM SMALLINT NOT NULL
    go

CREATE TYPE [PrimaryKey]
    FROM INT NOT NULL
    go

CREATE TYPE [InnateStat]
    FROM VARCHAR(5) NULL
    go

CREATE TYPE [InnateStatValue]
    FROM SMALLINT NULL
    go

CREATE TYPE [dAccount]
    FROM CHAR(5) NOT NULL
    go

CREATE TYPE [Username]
    FROM VARCHAR(20) NOT NULL
    go

CREATE TYPE [Password]
    FROM VARCHAR(20) NOT NULL
    go

CREATE TYPE [Email]
    FROM VARCHAR(50) NOT NULL
    go

CREATE TYPE [PrimaryKey6]
    FROM INT NOT NULL
    go

CREATE TYPE [PrimaryKey3]
    FROM INT NOT NULL
    go

CREATE TYPE [dMonster]
    FROM CHAR(5) NOT NULL
    go

CREATE TYPE [MonsterName]
    FROM NVARCHAR(20) NOT NULL
    go

CREATE TYPE [MonsterStatValue]
    FROM INTEGER NOT NULL
    go

CREATE TYPE [ForeignKey]
    FROM INT NOT NULL
    go

    sp_addsrvrolemember 'admin', 'sysadmin'
    go

CREATE TABLE [GameTool].[Account]
(
    [AccountId]          [PrimaryKey]  IDENTITY ( 10,1 ) ,
    [AccountUsername]    [Username] ,
    [AccountPassword]    [Password] ,
    [AccountEmail]       [Email] ,
    CONSTRAINT [XPKAccount] PRIMARY KEY  CLUSTERED ([AccountId] ASC)
    )
    go

CREATE TABLE [GameTool].[Rune]
(
    [RuneId]             [PrimaryKey6]  IDENTITY ( 1000000,1 ) ,
    [AccountId]          [PrimaryKey]  NULL
    INDEX [XIF1Rune] NONCLUSTERED  ,
    [RuneGrade]          [Grade]
    CONSTRAINT [CK_Grade_317387518]
    CHECK  ( [RuneGrade]=5 OR [RuneGrade]=6 ),
    [RuneSet]            [Set]
    CONSTRAINT [CK_Set_1348176969]
    CHECK  ( [RuneSet]='Violent' OR [RuneSet]='Swift' OR [RuneSet]='Rage' OR [RuneSet]='Will' OR [RuneSet]='Nemesis' OR [RuneSet]='Blade' ),
    [RunePosition]       [Position]
    CONSTRAINT [CK_Position_1736831478]
    CHECK  ( RunePosition BETWEEN 1 AND 6 ),
    [RuneInnate]         [Innate]
    CONSTRAINT [CK_Innate_433882015]
    CHECK  ( [RuneInnate]=0 OR [RuneInnate]=1 ),
    [RuneMainStat]       [Stat]
    CONSTRAINT [CK_Stat_16608635]
    CHECK  ( [RuneMainStat]='HP%' OR [RuneMainStat]='ATK%' OR [RuneMainStat]='DEF%' OR [RuneMainStat]='SPD' OR [RuneMainStat]='CRte' OR [RuneMainStat]='CDmg' OR [RuneMainStat]='RES' OR [RuneMainStat]='ACC' OR [RuneMainStat]='HP' OR [RuneMainStat]='ATK' OR [RuneMainStat]='DEF' ),
    [Runestat1]          [Stat]
    CONSTRAINT [CK_Stat_1659442414]
    CHECK  ( [Runestat1]='HP%' OR [Runestat1]='ATK%' OR [Runestat1]='DEF%' OR [Runestat1]='SPD' OR [Runestat1]='CRte' OR [Runestat1]='CDmg' OR [Runestat1]='RES' OR [Runestat1]='ACC' OR [Runestat1]='HP' OR [Runestat1]='ATK' OR [Runestat1]='DEF' ),
    [Runestat1val]       [StatValue]
    CONSTRAINT [CK_PositiveInteger_857815537]
    CHECK  ( Runestat1val >= 1 ),
    [Runestat2]          [Stat]
    CONSTRAINT [CK_Stat_1642665198]
    CHECK  ( [Runestat2]='HP%' OR [Runestat2]='ATK%' OR [Runestat2]='DEF%' OR [Runestat2]='SPD' OR [Runestat2]='CRte' OR [Runestat2]='CDmg' OR [Runestat2]='RES' OR [Runestat2]='ACC' OR [Runestat2]='HP' OR [Runestat2]='ATK' OR [Runestat2]='DEF' ),
    [Runestat2val]       [StatValue]
    CONSTRAINT [CK_PositiveInteger_857815793]
    CHECK  ( Runestat2val >= 1 ),
    [Runestat3]          [Stat]
    CONSTRAINT [CK_Stat_1625887982]
    CHECK  ( [Runestat3]='HP%' OR [Runestat3]='ATK%' OR [Runestat3]='DEF%' OR [Runestat3]='SPD' OR [Runestat3]='CRte' OR [Runestat3]='CDmg' OR [Runestat3]='RES' OR [Runestat3]='ACC' OR [Runestat3]='HP' OR [Runestat3]='ATK' OR [Runestat3]='DEF' ),
    [Runestat3val]       [StatValue]
    CONSTRAINT [CK_PositiveInteger_857816049]
    CHECK  ( Runestat3val >= 1 ),
    [Runestat4]          [Stat] ,
    [Runestat4val]       [StatValue]
    CONSTRAINT [CK_PositiveInteger_857816305]
    CHECK  ( Runestat4val >= 1 ),
    [Runeinnatestat]     [InnateStat] ,
    [Runeinnateval]      [InnateStatValue] ,
    CONSTRAINT [XPKRune] PRIMARY KEY  CLUSTERED ([RuneId] ASC),
    CONSTRAINT [R_16] FOREIGN KEY ([AccountId]) REFERENCES [GameTool].[Account]([AccountId])
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    go

CREATE TABLE [GameTool].[Monster]
(
    [MonsterId]          [PrimaryKey3]  IDENTITY ( 1000,1 ) ,
    [MonsterName]        [MonsterName] ,
    [MonsterHP]          [MonsterStatValue]
    CONSTRAINT [CK_StatValue_1345395308]
    CHECK  ( MonsterHP BETWEEN 0 AND 999999 ),
    [MonsterATK]         [MonsterStatValue]
    CONSTRAINT [CK_StatValue_827403536]
    CHECK  ( MonsterATK BETWEEN 0 AND 999999 ),
    [MonsterDEF]         [MonsterStatValue]
    CONSTRAINT [CK_StatValue_827210773]
    CHECK  ( MonsterDEF BETWEEN 0 AND 999999 ),
    [MonsterSPD]         [MonsterStatValue]
    CONSTRAINT [CK_StatValue_826224919]
    CHECK  ( MonsterSPD BETWEEN 0 AND 999999 ),
    [MonsterCRte]        [MonsterStatValue]
    CONSTRAINT [CK_StatValue_1331604439]
    CHECK  ( MonsterCRte BETWEEN 0 AND 999999 ),
    [MonsterCDmg]        [MonsterStatValue]
    CONSTRAINT [CK_StatValue_1332523733]
    CHECK  ( MonsterCDmg BETWEEN 0 AND 999999 ),
    [MonsterRES]         [MonsterStatValue]
    CONSTRAINT [CK_StatValue_826293256]
    CHECK  ( MonsterRES BETWEEN 0 AND 999999 ),
    [MonsterACC]         [MonsterStatValue]
    CONSTRAINT [CK_StatValue_827407896]
    CHECK  ( MonsterACC BETWEEN 0 AND 999999 ),
    CONSTRAINT [XPKMonster] PRIMARY KEY  CLUSTERED ([MonsterId] ASC)
    )
    go

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

INSERT INTO GameTool.Rune
(AccountId, RuneGrade, RuneSet, RunePosition, RuneInnate, RuneMainStat, Runestat1, Runestat1val, Runestat2, Runestat2val, Runestat3, Runestat3val, Runestat4, Runestat4val, Runeinnatestat, Runeinnateval)
VALUES
(1, 5, 'Violent', 3, 0, 'DEF%', 'ATK%', 8, 'ACC', 7, 'SPD', 6, 'CRte', 5, NULL, NULL),
(1, 5, 'Rage', 2, 1, 'ATK', 'DEF', 8, 'ACC', 10, 'SPD', 12, 'HP', 14, 'DEF', 10),
(1, 6, 'Swift', 5, 0, 'HP', 'SPD', 20, 'RES', 13, 'HP', 10, 'CDmg', 5, NULL, NULL),
(1, 5, 'Blade', 4, 1, 'CRte', 'DEF', 50, 'ATK', 69, 'SPD', 123, 'HP', 111, 'DEF', 101),
(1, 6, 'Blade', 6, 1, 'ATK%', 'HP', 8, 'DEF', 141, 'SPD', 12, 'HP', 14, 'DEF', 11),
(1, 5, 'Rage', 2, 1, 'ATK', 'DEF', 8, 'ACC', 11, 'SPD', 12, 'HP', 14, 'DEF', 11),
(10, 5, 'Rage', 2, 1, 'ATK', 'DEF', 8, 'ACC', 11, 'SPD', 12, 'HP', 14, 'DEF', 11),
(10, 5, 'Violent', 3, 0, 'DEF%', 'ATK%', 8, 'ACC', 7, 'SPD', 6, 'CRte', 5, NULL, NULL);


GO
