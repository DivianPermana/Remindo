package id.ac.unikom.remindo.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.ac.unikom.remindo.MainActivity;
import id.ac.unikom.remindo.Model.ToDo;
import id.ac.unikom.remindo.R;

class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
    ItemClickListener itemClickListener;
    TextView item_title, item_desc;

    public ListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        item_title = (TextView) itemView.findViewById(R.id.item_title);
        item_desc = (TextView) itemView.findViewById(R.id.item_desc);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select the Action");
        menu.add(0, 0, getAdapterPosition(), "Delete");
    }
}

public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    MainActivity mainActivity;
    List<ToDo> todoList;

    public ListItemAdapter(MainActivity mainActivity, List<ToDo> todoList) {
        this.mainActivity = mainActivity;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int i) {

        holder.item_title.setText(todoList.get(i).getTitle());
        holder.item_desc.setText(todoList.get(i).getDesc());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                mainActivity.title.setText(todoList.get(position).getTitle());
                mainActivity.desc.setText(todoList.get(position).getDesc());

                mainActivity.isUpdate = true;
                mainActivity.idUpdate = todoList.get(position).getId();

            }
        });


    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
