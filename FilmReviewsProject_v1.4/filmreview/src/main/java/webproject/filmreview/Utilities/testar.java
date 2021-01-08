package webproject.filmreview.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import webproject.filmreview.Models.Episode;
import webproject.filmreview.Models.Genres;
import webproject.filmreview.Models.Season;
import webproject.filmreview.Models.Series;
import webproject.filmreview.Models.User;
import webproject.filmreview.Models.WatchesEpisode;
import webproject.filmreview.Models.WatchesMovie;
import webproject.filmreview.Models.WatchesSeason;
import webproject.filmreview.Models.WatchesSeries;

public class testar 
{

    private Map<Long, User> users = Database.getAllUsers();
    private Map<Long, Series> seriesMap = Database.getAllSeries();

    public User getUser(long userId)
    {
        return users.get(userId);
    }

    public testar()
    {
        List<WatchesMovie> m = new ArrayList<>();
        List<WatchesSeries> sa = new ArrayList<>();
        User user = new User(Long.valueOf(1), "zoran", "123",m, sa);
        users.put(user.getId(), user);
        String sDate2 = "12/11/2002";
        Date date2 = null;
        try
        {
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        String sDate3 = "15/09/2005";
        Date date3 = null;
        try
        {
            date3 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        List<Season> seasonsList = new ArrayList<>();
        List<Episode> episodesList = new ArrayList<>();
        List<Genres> gnrs = new ArrayList<>();
        gnrs.add(Genres.BIOGRAPHY);
        gnrs.add(Genres.DRAMA);
        Series s = new Series(Long.valueOf(1), "Hopkins", (float)6.3, "disasta", seasonsList, gnrs);
        Season ss = new Season(Long.valueOf(1), 1, s.getId(), (float)3.3, "mahahaha", episodesList, "Jonathan, Diana");
        Episode ep1 = new Episode(1, ss.getId(), (float)5.4, "dasdasd", date2, 1);
        Episode ep2 = new Episode(2, ss.getId(), (float)2.2, "r23ew", date3, 2);
        episodesList.add(ep1);
        episodesList.add(ep2);
        ss.setEpisodes(episodesList);
        seasonsList.add(ss);
        s.setSeasons(seasonsList);
        seriesMap.put(s.getId(), s);
    }

    public boolean setSeasonAsFinished(long userId, long seriesId, long seasonId, String finishDate)
    {
        Date fDate = null;
        try 
        {
            fDate = new SimpleDateFormat("dd/MM/yyyy").parse(finishDate);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Series series = seriesMap.get(Long.valueOf(seriesId));
        if(series == null)
        {
            return false;
        }
        List<Season> seasonsList = series.getSeasons();
        Season ses = null;
        for(Season s : seasonsList)
        {
            if(s.getId() == seasonId)
            {
                ses = s;
            }
        }
        if(ses == null)
        {
            return false;
        }
        int count = ses.getEpisodes().size();
        List<WatchesSeries> watchList = user.getWatchedSeriesList();
        int j = 0;
        for(WatchesSeries s : watchList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> watchesSeasons = s.getSeasons();
                int i = 0;
                for(WatchesSeason ss : watchesSeasons)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        if(ss.getEpisodes().size() == count)
                        {
                            boolean flag = true;
                            List<WatchesEpisode> watchesEpisodes = ss.getEpisodes();
                            for(WatchesEpisode eps : watchesEpisodes)
                            {
                                if(!eps.getStatus())
                                {
                                    flag = false;
                                }
                                else if(eps.getFinishedAt().after(fDate))
                                {
                                    flag = false;
                                }
                            }
                            if(flag)
                            {
                                ss.setAsFinished(fDate);
                                watchesSeasons.set(i, ss);
                                s.setSeasons(watchesSeasons);
                                watchList.set(j, s);
                                user.setWatchedSeriesList(watchList);
                                users.put(userId, user);
                                return true;
                            }
                        }
                    }
                    i++;
                }
            }
            j++;
        }
        return false;
    }

    public boolean addSeriesToWatchList(long userId, long seriesId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            System.out.println("Null user.");
            return false;
        }
        List<WatchesSeries> watchSeriesList = user.getWatchedSeriesList();
        System.out.println(watchSeriesList.size());
        Series series = seriesMap.get(seriesId);
        if(series.equals(null))
        {
            System.out.print("Null series");
            return false;
        }
        for(WatchesSeries s : watchSeriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                System.out.println("Already added to watch list");
                return false;
            }
        }
        List<WatchesSeason> seasons = new ArrayList<>();
        WatchesSeries watchAdd = new WatchesSeries(watchSeriesList.size()+1, userId, seriesId, seasons);
        watchSeriesList.add(watchAdd);
        user.setWatchedSeriesList(watchSeriesList);
        users.put(userId, user);
        System.out.println("Series added");
        List<WatchesSeason> newses = users.get(userId).getWatchedSeriesList().get(0).getSeasons();
        System.out.println("new size: " + " " + newses.size());
        return true;
    }

    public boolean addSeasonToWatchList(long userId, long seriesId, long seasonId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Series series = seriesMap.get(seriesId);
        if(series.equals(null))
        {
            return false;
        }
        List<Season> seasonsList = series.getSeasons();
        Season ses = null;
        for(Season s : seasonsList)
        {
            if(s.getId() == seasonId)
            {
                ses = s;
            }
        }
        if(ses.equals(null))
        {
            return false;
        }
        List<WatchesSeries> watchSeriesList = user.getWatchedSeriesList();
        int i = 0;
        for(WatchesSeries s : watchSeriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> watchSeasons = s.getSeasons();
                for(WatchesSeason sea : watchSeasons)
                {
                    if(sea.getSeasonId() == seasonId)
                    {
                        return false;
                    }
                }
                List<WatchesEpisode> episList = new ArrayList<>();
                WatchesSeason ss = new WatchesSeason(watchSeasons.size()+1, s.getId(), seasonId, episList);
                watchSeasons.add(ss);
                s.setSeasons(watchSeasons);
                watchSeriesList.set(i, s);
                user.setWatchedSeriesList(watchSeriesList);
                users.put(userId, user);
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean addEpisodeToWatchList(long userId, long seriesId, long seasonId, long episodeId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Series series = seriesMap.get(seriesId);
        if(series.equals(null))
        {
            return false;
        }
        List<Season> seasonsList = series.getSeasons();
        Season ses = null;
        for(Season s : seasonsList)
        {
            if(s.getId() == seasonId)
            {
                ses = s;
            }
        }
        if(ses.equals(null))
        {
            return false;
        }
        Episode p = null;
        List<Episode> episodesList = ses.getEpisodes();
        for(Episode ep : episodesList)
        {
            if(ep.getId() == episodeId)
            {
                p = ep;
            }
        }
        if(p.equals(null))
        {
            return false;
        }
        List<WatchesSeries> seriesWatchList = user.getWatchedSeriesList();
        int j = 0;
        for(WatchesSeries s : seriesWatchList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> seasonsWatchList = s.getSeasons();
                int i = 0;
                for(WatchesSeason ss : seasonsWatchList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        List<WatchesEpisode> epsList = ss.getEpisodes();
                        for(WatchesEpisode epa : epsList)
                        {
                            if(epa.getEpisodeId() == episodeId)
                            {
                                return false;
                            }
                        }
                        WatchesEpisode toAdd = new WatchesEpisode(epsList.size()+1, ss.getId(), episodeId);
                        epsList.add(toAdd);
                        ss.setEpisodes(epsList);
                        seasonsWatchList.set(i, ss);
                        s.setSeasons(seasonsWatchList);
                        seriesWatchList.set(j, s);
                        user.setWatchedSeriesList(seriesWatchList);
                        users.put(userId, user);
                        return true;
                    }
                    i++;
                }
            }
            j++;
        }
        return false;
    }

    public boolean setEpisodeAsFinished(long userId, long seriesId, long seasonId, long episodeId, String finishDate)
    {
        Date fDate = null;
        try 
        {
            fDate = new SimpleDateFormat("dd/MM/yyyy").parse(finishDate);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Series series = seriesMap.get(Long.valueOf(seriesId));
        if(series == null)
        {
            return false;
        }
        List<Season> seasonsList = series.getSeasons();
        Season ses = null;
        for(Season s : seasonsList)
        {
            if(s.getId() == seasonId)
            {
                ses = s;
            }
        }
        if(ses == null)
        {
            return false;
        }
        Episode p = null;
        List<Episode> episodesList = ses.getEpisodes();
        for(Episode ep : episodesList)
        {
            if(ep.getId() == episodeId)
            {
                p = ep;
            }
        }
        if(p == null)
        {
            return false;
        }
        Date release = p.getReleaseDate();
        List<WatchesSeries> watchList = user.getWatchedSeriesList();
        int k = 0;
        for(WatchesSeries s : watchList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> sList = s.getSeasons();
                int j = 0;
                for(WatchesSeason ss : sList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        List<WatchesEpisode> epsList = ss.getEpisodes();
                        int i = 0;
                        for(WatchesEpisode ep : epsList)
                        {
                            if(ep.getEpisodeId() == episodeId)
                            {
                                if(release.after(fDate))
                                {
                                    return false;
                                }
                                else 
                                {
                                    ep.setAsFinished(fDate);
                                    epsList.set(i, ep);
                                    ss.setEpisodes(epsList);
                                    sList.set(j, ss);
                                    s.setSeasons(sList);
                                    watchList.set(k, s);
                                    user.setWatchedSeriesList(watchList);
                                    users.put(userId, user);
                                    return true; 
                                }
                            }
                            i++;
                        }
                    }
                }
                j++;
            }
            k++;
        }
        return false;
    }

    public static void main(String[] args) 
    {
        testar t = new testar();
        boolean addedSeries = t.addSeriesToWatchList(Long.valueOf(1), Long.valueOf(1));
        if(addedSeries)
        {
            boolean addSeason = t.addSeasonToWatchList(Long.valueOf(1), Long.valueOf(1), Long.valueOf(1));
            if(addSeason)
            {
                boolean addEpisode = t.addEpisodeToWatchList(Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), Long.valueOf(1));
                if(addEpisode)
                {
                    boolean addanother = t.addEpisodeToWatchList(Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), Long.valueOf(2));
                    if(addanother)
                    {
                        boolean finish = t.setEpisodeAsFinished(Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), "12/11/2015");
                        if(finish)
                        {
                            System.out.println("all good");
                            boolean finish1 = t.setEpisodeAsFinished(Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), Long.valueOf(2), "15/12/2016");
                            if(finish1)
                            {
                                boolean finish2 = t.setSeasonAsFinished(Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), "15/11/2017");
                                if(finish2)
                                {
                                System.out.println("noice");
                                }
                                /*
                                User us = t.getUser(Long.valueOf(1));
                                WatchesSeries s = us.getWatchedSeriesList().get(0);
                                WatchesSeason seas = s.getSeasons().get(0);
                                WatchesEpisode ep = seas.getEpisodes().get(0);
                                System.out.println(ep.getStatus());
                                */
                            }
                        }
                        else
                        {
                            System.out.println("no succsess.");
                        }
                    }
                    else
                    {
                        System.out.println("some fail");
                    }
                }
            }
        }
    }

    public Series fillSeriesWithWatchingItems(Series s, WatchesSeries ws)
    {
        System.out.println("Inside fill series");
        List<Season> seasons = new ArrayList<>();
        Series toReturn = new Series(s.getId(), s.getName(), s.getRating(), s.getDescription(), seasons, s.getGenres());
        List<WatchesSeason> sesList = ws.getSeasons();
        List<Season> seasonsList = s.getSeasons();
        if(sesList.size() == 0)
        {
            System.out.println("Use is not watching anything.");
            return toReturn;
        }
        for(WatchesSeason ses : sesList)
        {
            for(Season sea : seasonsList)
            {
                if(ses.getSeasonId() == sea.getId())
                {
                    List<Episode> episodes = new ArrayList<>();
                    Season newSeason = new Season(sea.getId(), sea.getSeasonNumber(), s.getId(), sea.getRating(), 
                    sea.getDescription(), sea.getEpisodes(), sea.getMainActors());
                    List<WatchesEpisode> watchesEpisodes = ses.getEpisodes();
                    for(WatchesEpisode ep : watchesEpisodes)
                    {
                        List<Episode> epsList = sea.getEpisodes();
                        for(Episode e : epsList)
                        {
                            if(ep.getEpisodeId() == e.getId())
                            {
                                episodes.add(e);
                            }
                        }
                    }
                    newSeason.setEpisodes(episodes);
                    seasons.add(newSeason);
                }
            }
        }
        toReturn.setSeasons(seasons);
        return toReturn;
    }

}