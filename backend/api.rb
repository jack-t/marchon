require "sinatra"
require "json"
require_relative "march.rb"

#enable :sessions
set :environment, :production
set :port, 8080

get '/marches/?' do
    March.all.to_json
end

get '/login/:username/?' do
    @username = params[:username]
    @user = Organizer.first(:username => @username)
    content_type :json
    {:organizer_id=>@user.id, :march_id => March.first.id}.to_json
end

get '/notifications/:march/?' do
    @march = March.get(params[:march])
    @notifs = @march.notifications
    @notifs.to_json
end

get '/events/:march/?' do
    @march = March.get(params[:march])
    @events = @march.events
    @events.to_json
end

get '/post_notification/:march/:title/:description/?' do
    @march = March.get(params[:march]) 
    @notification = @march.notifications.create(
        :title => params[:title],
        :description => params[:description]            
    )
    @notification.save!
    200 
end

get '/routes/:march/?' do
    @march = March.first(:id => params[:march])
    @march.routes.to_json
end

get '/notifications/:march' do
    @march = March.first(:id => params[:march])
    @march.notifications.to_json
end
