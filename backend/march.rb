require "data_mapper"
require "bcrypt"

DataMapper.setup(:default, 'sqlite::memory:')

class March
    include DataMapper::Resource

    property :id, Serial
    property :title, String
    property :lat, Decimal 
    property :long, Decimal 
    property :date, Date 

    has n, :events
    has n, :notifications
    has n, :routes
end

class Route
    include DataMapper::Resource
    property :id, Serial
    property :llat_begin, Decimal
    property :llong_begin, Decimal
    property :llat_end, Decimal
    property :llong_end, Decimal

    belongs_to :march
end

class Event
    include DataMapper::Resource

    property :id, Serial
    property :title, String
    property :description, String
    property :lat, Decimal
    property :long, Decimal
    property :time, DateTime
    belongs_to :march # FK
end

class Notification
    include DataMapper::Resource

    property :id, Serial
    property :title, String
    property :description, String
     
    belongs_to :march # FK
end

class Organizer
    include DataMapper::Resource
    include BCrypt
    property :id, Serial
    property :username, String
end

DataMapper.finalize # finish up the models
DataMapper.auto_upgrade!
# Setup initial data

user = Organizer.create(
    :username => "username"
)
user.save!

march = March.create(
    :title => "March Title",
    :lat => 100.0005,
    :long => 200,
    :date => Time.now
)
march.save!

user = Organizer.create(
    :username => "username"
)
user.save!

event = march.events.create(
    :title => "title",
    :description => "description"

)
event.save!

route = march.routes.create(
    :llat_begin => 38.908292,
    :llong_begin => -77.021489,
    :llat_end => 38.915292,
    :llong_end => -77.021489
)

route.save!
