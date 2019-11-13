require 'json'

#  exercise 1
arr = []
begin
  str = gets.chomp
  if str != ""
    arr.push str
  end
end while str != ""

file = File.open("users.txt", 'w')
file.puts arr
file.close

readArr = []
openFile = File.open("users.txt", 'r')
openFile.each {|line| readArr.push line}
puts readArr.sort

# exercise 2
x = gets.chomp.to_i
file = File.open("nums.txt", 'w')
(1..x).each { |i|
  file.puts rand(92919)
}
file.close

openFile = File.open("nums.txt", 'r')
sum =0
openFile.each {|num| sum +=num.to_i}
puts sum

# exercise 3
class User
  def initialize(u)
    @name = u[:name]
    @year = u[:year]
    @gender = u[:gender]
    @addr = u[:addr]
  end
  attr_reader
  attr_writer
end
file = File.open("users.txt", 'w')
(1..1).each do |i|
  printf "User %d \n", i
  name = gets.chomp
  year = gets.chomp
  gender = gets.chomp
  addr = gets.chomp
  user = {:name => name, :year => year, :gender => gender, :addr => addr}
  file.puts JSON.generate user
end
file.close

openFile = File.open "users.txt", 'r'
openFile.each do |line|
  user = JSON.parse(line)
  printf "%s,%s,%s,%s \n", user["name"], user["year"], user["gender"], user["addr"]
end

# exercise 4
x = gets.chomp
Dir.entries(x).select do |f|
  File.file? f
  puts f
end

# exercise 5
x = gets.chomp
x = x +'/*.rb'
Dir.entries(x).select do |f|
  File.file? f
  puts f
end