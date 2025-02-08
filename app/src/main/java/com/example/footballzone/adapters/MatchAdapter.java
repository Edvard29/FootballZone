package com.example.footballzone.adapters;  // Проверьте правильный пакет!

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.footballzone.R;
import com.example.footballzone.models.Match;  // ✅ Используйте правильный импорт

import java.util.List;



public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {
    private List<Match> matchList;

    public MatchAdapter(List<Match> matchList) {
        this.matchList = matchList;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matchList.get(position);
        holder.matchDate.setText(match.getMatchDate());
        holder.homeTeam.setText(match.getHomeTeam());
        holder.awayTeam.setText(match.getAwayTeam());
    }

    @Override
    public int getItemCount() {
        if (matchList == null) {
            return 0;
        }
        return matchList.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView matchDate, homeTeam, awayTeam;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            matchDate = itemView.findViewById(R.id.match_date);
            homeTeam = itemView.findViewById(R.id.home_team);
            awayTeam = itemView.findViewById(R.id.away_team);
        }
    }
}
