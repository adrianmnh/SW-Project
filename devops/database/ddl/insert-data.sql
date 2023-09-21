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
(1, 6, 'Nemesis', 5, 1, 'HP', 'RES', 12, 'SPD', 24, 'ATK%', 11, 'HP%', 14, 'DEF%', 7),
(10, 6, 'Blade', 2, 0, 'SPD', 'ATK%', 100, 'ATK', 15, 'CRte', 1, 'RES', 1, NULL, NULL),
(10, 6, 'Blade', 5, 0, 'HP', 'RES', 1, 'SPD', 1, 'ATK%', 100, 'ATK', 20, NULL, NULL);



GO