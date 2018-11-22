ALTER FUNCTION RE_RESOURCESFUNC(@rid         INT
                                , @ref        INT
                                , @resNr1     INT = -999999
                                , @resNr2     INT = 999999
                                , @descflag   INT = 3
                                , @resgrpflag INT = -1
                                , @restypflag INT = -1
                                , @rescaseflag INT = -1
                                , @resstateflag INT = -1
                                , @word       VARCHAR(500))
RETURNS TABLE
AS
RETURN
  SELECT id
  FROM   re_resourceitems rr
  WHERE  id = @rid
         AND resourceref = @ref
         AND resourceref IN
             (SELECT id
              FROM   re_resources
              WHERE  ( ( 1 = @descflag
                         AND description IS NULL )
                        OR ( 2 = @descflag
                             AND description IS NOT NULL )
                        OR ( 3 = @descflag
                             AND ( description IS NOT NULL
                                    OR description = '-' ) ) )
                     AND ( resourcenr BETWEEN @resNr1 AND @resNr2 )
                     AND ( ( -1 = @resgrpFlag
                             AND resourcegroup IN( 'UN', 'HR', 'UNRP', 'HRRP',
                                                                    'SS', 'HELP', 'MISC' ) )
                            OR ( 1 = @resgrpFlag
                                 AND resourcegroup = 'UN' )
                            OR ( 2 = @resgrpFlag
                                 AND resourcegroup = 'HR' )
                            OR ( 3 = @resgrpFlag
                                 AND resourcegroup = 'UNRP' )
                            OR ( 4 = @resgrpFlag
                                 AND resourcegroup = 'HRRP' )
                            OR ( 5 = @resgrpFlag
                                 AND resourcegroup = 'SS' )
                            OR ( 6 = @resgrpFlag
                                 AND resourcegroup = 'HELP' )
                            OR ( 7 = @resgrpFlag
                                 AND resourcegroup = 'MISC' ) )
                     AND ( ( -1 = @restypflag
                             AND resourcetype IN( 1, 2 ) )
                            OR ( 1 = @restypflag
                                 AND resourcetype = 1 )
                            OR ( 2 = @restypflag
                                 AND resourcetype = 2 ) )
                     AND ( ( -1 = @rescaseflag
                             AND resourcecase IN( 1, 2, 3, 4, 5 ) )
                            OR ( 1 = @rescaseflag
                                 AND resourcecase = 1 )
                            OR ( 2 = @rescaseflag
                                 AND resourcecase = 2 )
                            OR ( 3 = @rescaseflag
                                 AND resourcecase = 3 )
                            OR ( 4 = @rescaseflag
                                 AND resourcecase = 4 )
                            OR ( 5 = @rescaseflag
                                 AND resourcecase = 5 ) )
                     AND ( ( -1 = @resstateflag
                             AND active IN( 0, 1 ) )
                            OR ( 0 = @resstateflag
                                 AND active = 0 )
                            OR ( 1 = @resstateflag
                                 AND active = 1 ) )
              UNION
              SELECT id
              FROM   re_resources
              WHERE  ( ( 4 = @descflag
                         AND description LIKE '%' + @word + '%' )
                        OR ( 5 = @descflag
                             AND description NOT LIKE '%' + @word + '%' )
                        OR ( 6 = @descflag
                             AND description LIKE @word + '%' )
                        OR ( 7 = @descflag
                             AND description NOT LIKE @word + '%' )
                        OR ( 8 = @descflag
                             AND description LIKE '%' + @word )
                        OR ( 9 = @descflag
                             AND description NOT LIKE '%' + @word )
                        OR ( 10 = @descflag
                             AND description = @word )
                        OR ( 11 = @descflag
                             AND description <> @word ) )
                     AND ( resourcenr BETWEEN @resNr1 AND @resNr2 )
                     AND ( ( -1 = @resgrpFlag
                             AND resourcegroup IN( 'UN', 'HR', 'UNRP', 'HRRP',
                                                       'SS', 'HELP', 'MISC' ) )
                            OR ( 1 = @resgrpFlag
                                 AND resourcegroup = 'UN' )
                            OR ( 2 = @resgrpFlag
                                 AND resourcegroup = 'HR' )
                            OR ( 3 = @resgrpFlag
                                 AND resourcegroup = 'UNRP' )
                            OR ( 4 = @resgrpFlag
                                 AND resourcegroup = 'HRRP' )
                            OR ( 5 = @resgrpFlag
                                 AND resourcegroup = 'SS' )
                            OR ( 6 = @resgrpFlag
                                 AND resourcegroup = 'HELP' )
                            OR ( 7 = @resgrpFlag
                                 AND resourcegroup = 'MISC' ) )
                     AND ( ( -1 = @restypflag
                             AND resourcetype IN( 1, 2 ) )
                            OR ( 1 = @restypflag
                                 AND resourcetype = 1 )
                            OR ( 2 = @restypflag
                                 AND resourcetype = 2 ) )
                     AND ( ( -1 = @rescaseflag
                             AND resourcecase IN( 1, 2, 3, 4, 5 ) )
                            OR ( 1 = @rescaseflag
                                 AND resourcecase = 1 )
                            OR ( 2 = @rescaseflag
                                 AND resourcecase = 2 )
                            OR ( 3 = @rescaseflag
                                 AND resourcecase = 3 )
                            OR ( 4 = @rescaseflag
                                 AND resourcecase = 4 )
                            OR ( 5 = @rescaseflag
                                 AND resourcecase = 5 ) )
                     AND ( ( -1 = @resstateflag
                             AND active IN( 0, 1 ) )
                            OR ( 0 = @resstateflag
                                 AND active = 0 )
                            OR ( 1 = @resstateflag
                                 AND active = 1 ) )); 

ALTER FUNCTION RE_RESOURCEITEMFUNC(@rid             INT,
							       @ordernr1        INT          = -999999,
							       @ordernr2        INT          = 999999,
							       @tagnr1          INT          = -2147483647,
							       @tagnr2          INT          = 2147483647,
							       @levelnr1        INT          = 0,
							       @levelnr2        INT          = 999,
								   @prefixflag      INT          = 3,
							       @infoflag        INT          = 3,
							       @resitemcaseflag INT          = -1,
							       @prefixComboText VARCHAR(500) = '',
							       @infoComboText   VARCHAR(500) = '')
RETURNS TABLE
AS
RETURN
  SELECT id
  FROM   re_resourceitems
  WHERE  id = @rid
         AND ( ( 1 = @prefixflag
                 AND prefixstr IS NULL )
                OR ( 2 = @prefixflag
                     AND prefixstr IS NOT NULL )
                OR ( 3 = @prefixflag
                     AND ( prefixstr IS NOT NULL
                            OR prefixstr = '-' ) ) )
         AND ( ( 1 = @infoflag
                 AND info IS NULL )
                OR ( 2 = @infoflag
                     AND info IS NOT NULL )
                OR ( 3 = @infoflag
                     AND ( info IS NOT NULL
                            OR info = '-' ) ) )
         AND ( ( -1 = @resitemcaseflag
                 AND resourcecase IN( 0, 1, 2, 3,
                                      4, 5 ) )
                OR ( 1 = @resitemcaseflag
                     AND resourcecase = 1 )
                OR ( 2 = @resitemcaseflag
                     AND resourcecase = 2 )
                OR ( 3 = @resitemcaseflag
                     AND resourcecase = 3 )
                OR ( 4 = @resitemcaseflag
                     AND resourcecase = 4 )
                OR ( 5 = @resitemcaseflag
                     AND resourcecase = 5 ) )
         AND ( ordernr BETWEEN @ordernr1 AND @ordernr2 )
         AND ( tagnr BETWEEN @tagnr1 AND @tagnr2 )
         AND ( levelnr BETWEEN @levelnr1 AND @levelnr2 )
  UNION
  SELECT id
  FROM   re_resourceitems
  WHERE  id = @rid
         AND ( ( 4 = @prefixflag
                 AND prefixstr LIKE '%' + @prefixComboText + '%' )
                OR ( 5 = @prefixflag
                     AND prefixstr NOT LIKE '%' + @prefixComboText + '%' )
                OR ( 6 = @prefixflag
                     AND prefixstr LIKE @prefixComboText + '%' )
                OR ( 7 = @prefixflag
                     AND prefixstr NOT LIKE @prefixComboText + '%' )
                OR ( 8 = @prefixflag
                     AND prefixstr LIKE '%' + @prefixComboText )
                OR ( 9 = @prefixflag
                     AND prefixstr NOT LIKE '%' + @prefixComboText )
                OR ( 10 = @prefixflag
                     AND prefixstr = @prefixComboText )
                OR ( 11 = @prefixflag
                     AND prefixstr <> @prefixComboText ) )
         AND ( ( 4 = @infoflag
                 AND info LIKE '%' + @infoComboText + '%' )
                OR ( 5 = @infoflag
                     AND info NOT LIKE '%' + @infoComboText + '%' )
                OR ( 6 = @infoflag
                     AND info LIKE @infoComboText + '%' )
                OR ( 7 = @infoflag
                     AND info NOT LIKE @infoComboText + '%' )
                OR ( 8 = @infoflag
                     AND info LIKE '%' + @infoComboText )
                OR ( 9 = @infoflag
                     AND info NOT LIKE '%' + @infoComboText )
                OR ( 10 = @infoflag
                     AND info = @infoComboText )
                OR ( 11 = @infoflag
                     AND info <> @infoComboText ) )
         AND ( ( -1 = @resitemcaseflag
                 AND resourcecase IN( 0, 1, 2, 3,
                                      4, 5 ) )
                OR ( 1 = @resitemcaseflag
                     AND resourcecase = 1 )
                OR ( 2 = @resitemcaseflag
                     AND resourcecase = 2 )
                OR ( 3 = @resitemcaseflag
                     AND resourcecase = 3 )
                OR ( 4 = @resitemcaseflag
                     AND resourcecase = 4 )
                OR ( 5 = @resitemcaseflag
                     AND resourcecase = 5 ) )
         AND ( ordernr BETWEEN @ordernr1 AND @ordernr2 )
         AND ( tagnr BETWEEN @tagnr1 AND @tagnr2 )
         AND ( levelnr BETWEEN @levelnr1 AND @levelnr2 ); 
