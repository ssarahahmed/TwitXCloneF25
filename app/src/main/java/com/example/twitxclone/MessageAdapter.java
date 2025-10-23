package com.example.twitxclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.twitxclone.model.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {
    Context context;
    private List<Message> messages;
    private final SimpleDateFormat dateFormat;

    public MessageAdapter(@NonNull Context context, @NonNull List<Message> messages) {
        super(context, R.layout.list_message, messages);
        this.context = context;
        this.messages = messages;
        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_message, parent);
        TextView messageView = (TextView) rowView.findViewById(R.id.message_field);
        TextView dateView = (TextView) rowView.findViewById(R.id.date_field);
        TextView userView = (TextView) rowView.findViewById(R.id.usersay);

        Message current = messages.get(position);
        String author = "None";
        if(current.getPublishedAt() != null) {
            author = current.getAuthor();
        }
        userView.setText(author);

        String date = "0/0/0000";
        if(current.getPublishedAt() != null) {
            date= dateFormat.format(new Date(current.getPublishedAt()));
        }
        dateView.setText(date);

        messageView.setText(current.getMessage());
        return rowView;
    }
}
