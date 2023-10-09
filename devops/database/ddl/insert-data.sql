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

PRINT('Inserting Monster data')
GO

INSERT INTO GameTool.Monster
VALUES
    ('Akroma', '10710', '681', '747', '116', '15', '50', '15', '0'),
    ('Amarna', '9885', '681', '637', '101', '15', '50', '15', '0'),
    ('Artamiel', '11535', '604', '769', '95', '15', '50', '40', '0'),
    ('Asima', '10710', '812', '615', '104', '15', '50', '15', '25'),
    ('Bael', '10215', '856', '604', '104', '30', '50', '15', '0'),
    ('Barbara', '9720', '845', '648', '108', '15', '50', '15', '25'),
    ('Baretta', '11205', '681', '549', '105', '15', '50', '15', '0'),
    ('Bastet', '11850', '637', '714', '99', '15', '50', '40', '0'),
    ('Bellenus', '10215', '703', '758', '99', '15', '50', '15', '0'),
    ('Camilla', '12015', '714', '626', '101', '30', '50', '15', '0'),
    ('Cadiz', '11205', '856', '538', '99', '15', '50', '15', '0'),
    ('Chandra', '13170', '604', '659', '96', '15', '50', '15', '0'),
    ('Chiwu', '12180', '780', '549', '103', '15', '50', '15', '40'),
    ('Craka', '11535', '769', '604', '104', '15', '50', '15', '0'),
    ('Dark-Ryu', '10875', '823', '593', '103', '15', '50', '40', '15'),
    ('Elsharion', '10545', '780', '659', '100', '15', '50', '15', '25'),
    ('Ethna', '10380', '845', '604', '119', '30', '50', '15', '0'),
    ('Jaara', '10215', '1021', '439', '99', '15', '50', '15', '0'),
    ('Jager', '12675', '670', '626', '100', '30', '50', '15', '0'),
    ('Julianne', '12345', '823', '494', '99', '15', '50', '15', '0'),
    ('Kumar', '13005', '593', '681', '96', '15', '50', '15', '0'),
    ('Laika', '11040', '834', '571', '100', '15', '50', '15', '0'),
    ('Leo', '11850', '714', '637', '100', '15', '50', '40', '0'),
    ('Lucifer', '11700', '725', '637', '104', '15', '50', '40', '0'),
    ('Miho', '9060', '703', '505', '119', '15', '50', '15', '0'),
    ('Nigong', '12840', '670', '615', '103', '15', '50', '40', '0'),
    ('Oberon', '10050', '790', '681', '98', '30', '50', '15', '0'),
    ('Okeanos', '10710', '769', '659', '101', '30', '50', '15', '0'),
    ('Perna', '12345', '878', '439', '109', '15', '50', '15', '0'),
    ('Poseidon', '10050', '790', '681', '101', '15', '50', '15', '25'),
    ('Ragdoll', '11535', '714', '659', '100', '30', '50', '15', '0'),
    ('Rahul', '12840', '659', '626', '96', '15', '50', '15', '0'),
    ('Rica', '10215', '823', '637', '105', '15', '50', '15', '25'),
    ('Ritesh', '13500', '637', '604', '96', '15', '50', '15', '0'),
    ('Seara', '10875', '801', '615', '100', '15', '50', '15', '25'),
    ('Shan', '10215', '812', '648', '95', '15', '50', '15', '25'),
    ('Son-Zhang-Lao', '12180', '692', '637', '118', '15', '50', '15', '0'),
    ('Susano', '8070', '911', '527', '107', '15', '50', '15', '25'),
    ('Sylvia', '11040', '692', '714', '104', '15', '50', '15', '0'),
    ('Thebae', '12675', '692', '604', '101', '15', '50', '15', '0'),
    ('Theomars', '10875', '823', '593', '100', '30', '50', '15', '0'),
    ('Tiana', '11850', '725', '626', '96', '15', '50', '40', '0'),
    ('Trinity', '11700', '758', '604', '101', '30', '50', '15', '0'),
    ('Triton', '10875', '681', '736', '116', '15', '50', '15', '0'),
    ('Vanessa', '10875', '703', '714', '101', '15', '50', '40', '0'),
    ('Velajuel', '10050', '681', '790', '100', '30', '50', '15', '0'),
    ('Verdehile', '9885', '812', '505', '99', '15', '50', '15', '0'),
    ('Veromos', '9555', '637', '703', '99', '15', '50', '15', '0'),
    ('Wolyung', '10710', '823', '604', '101', '15', '50', '15', '0'),
    ('Woonsa', '12015', '703', '637', '118', '15', '50', '15', '0'),
    ('Woosa', '12015', '659', '681', '118', '15', '50', '15', '0'),
    ('Yeonhong', '11700', '736', '626', '116', '15', '50', '15', '0'),
    ('Zerath', '15315', '560', '560', '98', '30', '50', '15', '0'),
    ('Zeratu', '10710', '790', '637', '95', '30', '50', '15', '0');



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
(1, 6, 'Blade', 2, 0, 'SPD', 'ATK%', 100, 'ATK', 15, 'CRte', 1, 'RES', 1, NULL, NULL),
(1, 6, 'Blade', 5, 0, 'HP', 'RES', 1, 'SPD', 1, 'ATK%', 100, 'ATK', 20, NULL, NULL);



GO

INSERT INTO GameTool.Summon (AccountId, MonsterId, Name, Rune1, Rune2, Rune3, Rune4, Rune5, Rune6)
VALUES
    (1, 64, N'Zeratu', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 62, N'Yeonhong', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 43, N'Rica', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 51, N'Theomars', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 38, N'Okeanos', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 37, N'Oberon', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 20, N'Camilla', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 21, N'Cadiz', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 13, N'Artamiel', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 14, N'Asima', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 18, N'Bastet', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 26, N'Elsharion', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 27, N'Ethna', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 45, N'Seara', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 11, N'Akroma', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 12, N'Amarna', NULL, NULL, NULL, NULL, NULL, NULL),
    (1, 14, N'AsimaSPD', NULL, NULL, NULL, NULL, NULL, NULL);

GO
