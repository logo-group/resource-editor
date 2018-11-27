CREATE FUNCTION RE_RESOURCESFUNC(@rid         INT
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
  SELECT ID
  FROM   RE_RESOURCEITEMS rr
  WHERE  ID = @rid
         AND RESOURCEREF = @ref
         AND RESOURCEREF IN
             (SELECT ID
              FROM   RE_RESOURCES
              WHERE  ( ( 1 = @descflag
                         AND DESCRIPTION IS NULL )
                        OR ( 2 = @descflag
                             AND DESCRIPTION IS NOT NULL )
                        OR ( 3 = @descflag
                             AND ( DESCRIPTION IS NOT NULL
                                    OR DESCRIPTION = '-' ) ) )
                     AND ( RESOURCENR BETWEEN @resNr1 AND @resNr2 )
                     AND ( ( -1 = @resgrpFlag
                             AND RESOURCEGROUP IN( 'UN', 'HR', 'UNRP', 'HRRP',
                                                                    'SS', 'HELP', 'MISC' ) )
                            OR ( 1 = @resgrpFlag
                                 AND RESOURCEGROUP = 'UN' )
                            OR ( 2 = @resgrpFlag
                                 AND RESOURCEGROUP = 'HR' )
                            OR ( 3 = @resgrpFlag
                                 AND RESOURCEGROUP = 'UNRP' )
                            OR ( 4 = @resgrpFlag
                                 AND RESOURCEGROUP = 'HRRP' )
                            OR ( 5 = @resgrpFlag
                                 AND RESOURCEGROUP = 'SS' )
                            OR ( 6 = @resgrpFlag
                                 AND RESOURCEGROUP = 'HELP' )
                            OR ( 7 = @resgrpFlag
                                 AND RESOURCEGROUP = 'MISC' ) )
                     AND ( ( -1 = @restypflag
                             AND RESOURCETYPE IN( 1, 2 ) )
                            OR ( 1 = @restypflag
                                 AND RESOURCETYPE = 1 )
                            OR ( 2 = @restypflag
                                 AND RESOURCETYPE = 2 ) )
                     AND ( ( -1 = @rescaseflag
                             AND RESOURCECASE IN( 1, 2, 3, 4, 5 ) )
                            OR ( 1 = @rescaseflag
                                 AND RESOURCECASE = 1 )
                            OR ( 2 = @rescaseflag
                                 AND RESOURCECASE = 2 )
                            OR ( 3 = @rescaseflag
                                 AND RESOURCECASE = 3 )
                            OR ( 4 = @rescaseflag
                                 AND RESOURCECASE = 4 )
                            OR ( 5 = @rescaseflag
                                 AND RESOURCECASE = 5 ) )
                     AND ( ( -1 = @resstateflag
                             AND ACTIVE IN( 0, 1 ) )
                            OR ( 0 = @resstateflag
                                 AND ACTIVE = 0 )
                            OR ( 1 = @resstateflag
                                 AND ACTIVE = 1 ) )
              UNION
              SELECT ID
              FROM   RE_RESOURCES
              WHERE  ( ( 4 = @descflag
                         AND DESCRIPTION LIKE '%' + @word + '%' )
                        OR ( 5 = @descflag
                             AND DESCRIPTION NOT LIKE '%' + @word + '%' )
                        OR ( 6 = @descflag
                             AND DESCRIPTION LIKE @word + '%' )
                        OR ( 7 = @descflag
                             AND DESCRIPTION NOT LIKE @word + '%' )
                        OR ( 8 = @descflag
                             AND DESCRIPTION LIKE '%' + @word )
                        OR ( 9 = @descflag
                             AND DESCRIPTION NOT LIKE '%' + @word )
                        OR ( 10 = @descflag
                             AND DESCRIPTION = @word )
                        OR ( 11 = @descflag
                             AND DESCRIPTION <> @word ) )
                     AND ( RESOURCENR BETWEEN @resNr1 AND @resNr2 )
                     AND ( ( -1 = @resgrpFlag
                             AND RESOURCEGROUP IN( 'UN', 'HR', 'UNRP', 'HRRP',
                                                       'SS', 'HELP', 'MISC' ) )
                            OR ( 1 = @resgrpFlag
                                 AND RESOURCEGROUP = 'UN' )
                            OR ( 2 = @resgrpFlag
                                 AND RESOURCEGROUP = 'HR' )
                            OR ( 3 = @resgrpFlag
                                 AND RESOURCEGROUP = 'UNRP' )
                            OR ( 4 = @resgrpFlag
                                 AND RESOURCEGROUP = 'HRRP' )
                            OR ( 5 = @resgrpFlag
                                 AND RESOURCEGROUP = 'SS' )
                            OR ( 6 = @resgrpFlag
                                 AND RESOURCEGROUP = 'HELP' )
                            OR ( 7 = @resgrpFlag
                                 AND RESOURCEGROUP = 'MISC' ) )
                     AND ( ( -1 = @restypflag
                             AND RESOURCETYPE IN( 1, 2 ) )
                            OR ( 1 = @restypflag
                                 AND RESOURCETYPE = 1 )
                            OR ( 2 = @restypflag
                                 AND RESOURCETYPE = 2 ) )
                     AND ( ( -1 = @rescaseflag
                             AND RESOURCECASE IN( 1, 2, 3, 4, 5 ) )
                            OR ( 1 = @rescaseflag
                                 AND RESOURCECASE = 1 )
                            OR ( 2 = @rescaseflag
                                 AND RESOURCECASE = 2 )
                            OR ( 3 = @rescaseflag
                                 AND RESOURCECASE = 3 )
                            OR ( 4 = @rescaseflag
                                 AND RESOURCECASE = 4 )
                            OR ( 5 = @rescaseflag
                                 AND RESOURCECASE = 5 ) )
                     AND ( ( -1 = @resstateflag
                             AND ACTIVE IN( 0, 1 ) )
                            OR ( 0 = @resstateflag
                                 AND ACTIVE = 0 )
                            OR ( 1 = @resstateflag
                                 AND ACTIVE = 1 ) )); 

CREATE FUNCTION RE_RESOURCEITEMFUNC(@rid             INT,
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
  SELECT ID
  FROM   RE_RESOURCEITEMS
  WHERE  ID = @rid
         AND ( ( 1 = @prefixflag
                 AND PREFIXSTR IS NULL )
                OR ( 2 = @prefixflag
                     AND PREFIXSTR IS NOT NULL )
                OR ( 3 = @prefixflag
                     AND ( PREFIXSTR IS NOT NULL
                            OR PREFIXSTR = '-' ) ) )
         AND ( ( 1 = @infoflag
                 AND INFO IS NULL )
                OR ( 2 = @infoflag
                     AND INFO IS NOT NULL )
                OR ( 3 = @infoflag
                     AND ( INFO IS NOT NULL
                            OR INFO = '-' ) ) )
         AND ( ( -1 = @resitemcaseflag
                 AND RESOURCECASE IN( 0, 1, 2, 3,
                                      4, 5 ) )
                OR ( 1 = @resitemcaseflag
                     AND RESOURCECASE = 1 )
                OR ( 2 = @resitemcaseflag
                     AND RESOURCECASE = 2 )
                OR ( 3 = @resitemcaseflag
                     AND RESOURCECASE = 3 )
                OR ( 4 = @resitemcaseflag
                     AND RESOURCECASE = 4 )
                OR ( 5 = @resitemcaseflag
                     AND RESOURCECASE = 5 ) )
         AND ( ORDERNR BETWEEN @ordernr1 AND @ordernr2 )
         AND ( TAGNR BETWEEN @tagnr1 AND @tagnr2 )
         AND ( LEVELNR BETWEEN @levelnr1 AND @levelnr2 )
  UNION
  SELECT ID
  FROM   RE_RESOURCEITEMS
  WHERE  ID = @rid
         AND ( ( 4 = @prefixflag
                 AND PREFIXSTR LIKE '%' + @prefixComboText + '%' )
                OR ( 5 = @prefixflag
                     AND PREFIXSTR NOT LIKE '%' + @prefixComboText + '%' )
                OR ( 6 = @prefixflag
                     AND PREFIXSTR LIKE @prefixComboText + '%' )
                OR ( 7 = @prefixflag
                     AND PREFIXSTR NOT LIKE @prefixComboText + '%' )
                OR ( 8 = @prefixflag
                     AND PREFIXSTR LIKE '%' + @prefixComboText )
                OR ( 9 = @prefixflag
                     AND PREFIXSTR NOT LIKE '%' + @prefixComboText )
                OR ( 10 = @prefixflag
                     AND PREFIXSTR = @prefixComboText )
                OR ( 11 = @prefixflag
                     AND PREFIXSTR <> @prefixComboText ) )
         AND ( ( 4 = @infoflag
                 AND INFO LIKE '%' + @infoComboText + '%' )
                OR ( 5 = @infoflag
                     AND INFO NOT LIKE '%' + @infoComboText + '%' )
                OR ( 6 = @infoflag
                     AND INFO LIKE @infoComboText + '%' )
                OR ( 7 = @infoflag
                     AND INFO NOT LIKE @infoComboText + '%' )
                OR ( 8 = @infoflag
                     AND INFO LIKE '%' + @infoComboText )
                OR ( 9 = @infoflag
                     AND INFO NOT LIKE '%' + @infoComboText )
                OR ( 10 = @infoflag
                     AND INFO = @infoComboText )
                OR ( 11 = @infoflag
                     AND INFO <> @infoComboText ) )
         AND ( ( -1 = @resitemcaseflag
                 AND RESOURCECASE IN( 0, 1, 2, 3,
                                      4, 5 ) )
                OR ( 1 = @resitemcaseflag
                     AND RESOURCECASE = 1 )
                OR ( 2 = @resitemcaseflag
                     AND RESOURCECASE = 2 )
                OR ( 3 = @resitemcaseflag
                     AND RESOURCECASE = 3 )
                OR ( 4 = @resitemcaseflag
                     AND RESOURCECASE = 4 )
                OR ( 5 = @resitemcaseflag
                     AND RESOURCECASE = 5 ) )
         AND ( ORDERNR BETWEEN @ordernr1 AND @ordernr2 )
         AND ( TAGNR BETWEEN @tagnr1 AND @tagnr2 )
         AND ( LEVELNR BETWEEN @levelnr1 AND @levelnr2 ); 
